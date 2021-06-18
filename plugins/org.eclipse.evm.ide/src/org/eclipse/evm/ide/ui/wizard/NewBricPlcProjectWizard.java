package org.eclipse.evm.ide.ui.wizard;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.evm.ide.ui.*;
import org.eclipse.evm.ide.ui.commands.NewAppCommand;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class NewBricPlcProjectWizard extends Wizard implements INewWizard {

	/** The page. */
	private NewBricPlcProjectPage page;

	/**
	 * Instantiates a new new system wizard.
	 */
	public NewBricPlcProjectWizard() {
		setWindowTitle(Messages.New4diacProjectWizard_WizardName);
	}
	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		page = new NewBricPlcProjectPage(Messages.New4diacProjectWizard_WizardName);
		page.setTitle(Messages.New4diacProjectWizard_WizardName);
		page.setDescription(Messages.New4diacProjectWizard_WizardDesc);

		addPage(page);
	}
	
	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		try {
			WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
				@Override
				protected void execute(IProgressMonitor monitor) {
					createProject(monitor != null ? monitor : new NullProgressMonitor());
				}
			};
			getContainer().run(false, true, op);
		} catch (InvocationTargetException | InterruptedException x) {
			return false;
		}
		// everything worked fine
		return true;
	}
	
	/**
	 * Creates a new project in the workspace.
	 *
	 * @param monitor the monitor
	 */
	private void createProject(final IProgressMonitor monitor) {
		try {

			IProject newProject = SystemManager.INSTANCE.createNew4diacProject(page.getProjectName(),
					page.getLocationPath(), page.importDefaultPalette(), monitor);
			AutomationSystem system = SystemManager.INSTANCE.createNewSystem(newProject, page.getInitialSystemName());
			TypeManagementPreferencesHelper.setupVersionInfo(system);
			createInitialApplication(monitor, system);
		} catch (CoreException e) {
			Activator.getDefault().logError(e.getMessage(), e);
		} finally {
			monitor.done();
		}
	}
	
	private void createInitialApplication(final IProgressMonitor monitor, AutomationSystem system) {
		NewAppCommand cmd = new NewAppCommand(system, page.getInitialApplicationName(), ""); //$NON-NLS-1$
		cmd.execute(monitor, null);

		Application app = cmd.getApplication();
		if (page.getOpenApplication() && null != app) {
			OpenListenerManager.openEditor(app);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 * org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init(final IWorkbench workbench, final IStructuredSelection selection) {
		// currently nothing to do here
	}
}
