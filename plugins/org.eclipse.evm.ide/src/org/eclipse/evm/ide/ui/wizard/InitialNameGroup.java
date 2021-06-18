package org.eclipse.evm.ide.ui.wizard;

import org.eclipse.evm.ide.model.IdentifierVerifyer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class InitialNameGroup extends Composite {

	private Text name;

	// flag indicating if the user changed the initial application name if yes do
	// not update the name any more
	private boolean nameManuallyChanged = false;

	private boolean blockListeners = false;

	private Listener applicationNameModifyListener = e -> {
		if (!blockListeners) {
			nameManuallyChanged = true;
		}
	};

	public InitialNameGroup(Composite parent, String labelText) {
		super(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		setLayout(layout);
		setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label applicationLabel = new Label(this, SWT.NONE);
		applicationLabel.setText(labelText);
		applicationLabel.setFont(parent.getFont());

		// new project name entry field
		name = new Text(this, SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		name.setLayoutData(data);
		name.setFont(parent.getFont());
		name.addListener(SWT.Modify, applicationNameModifyListener);
	}

	public String getInitialName() {
		return name.getText();
	}

	public boolean validateName(String projectName) {
		if (!nameManuallyChanged) {
			blockListeners = true;
			name.setText(projectName);
			blockListeners = false;
		} else if (!IdentifierVerifyer.isValidIdentifier(getInitialName())) {
			return false;
		}
		return true;
	}

	public void addNameModifyListener(Listener nameModifyListener) {
		name.addListener(SWT.Modify, nameModifyListener);
	}

}
