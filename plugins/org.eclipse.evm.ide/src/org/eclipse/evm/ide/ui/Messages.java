package org.eclipse.evm.ide.ui;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.evm.ide.ui.messages"; //$NON-NLS-1$

	public static String NewApplicationWizardTitle;

	public static String NewApplicationPage_NewApplicationDescription;

	/** The New application page_ error message_ empty app name. */
	public static String NewApplicationPage_ErrorMessage_EmptyElementName;

	/** The New application page_ error message invalid app name. */
	public static String NewApplicationPage_ErrorMessageInvalidAppName;

	public static String NewApplicationPage_OpenApplicationForEditing;

	/** The New application page_ error message no system selected. */
	public static String NewApplicationPage_ErrorMessageNoSystemSelected;

	public static String NewApplicationCommand_LABEL_NewApplication;

	public static String NewSystemWizard_ShowAdvanced;

	public static String NewSystemWizard_HideAdvanced;

	/** The New system wizard_ wizard desc. */
	public static String New4diacProjectWizard_WizardDesc;

	/** The New system wizard_ wizard name. */
	public static String New4diacProjectWizard_WizardName;

	public static String New4diacProjectWizard_InitialSystemName;
	public static String New4diacProjectWizard_InitialApplicationName;

	public static String NewSystemWizardPage_CreateNewSystem;

	/** The Palette management page_ labe l_ default palette. */
	public static String PaletteManagementPage_LABEL_DefaultTypeLibrary;

	public static String SystemNameNotValid;

	public static String AutomationSystemEditor_Title;
	public static String AutomationSystemEditor_Info;
	public static String AutomationSystemEditor_Discard_Changes;
	public static String AutomationSystemEditor_Save_Changes;
	public static String AutomationSystemEditor_Overwrite_Changes;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
		// empty private constructor
	}
}
