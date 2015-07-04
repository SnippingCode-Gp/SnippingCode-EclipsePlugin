package guitest.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.json.JSONException;

import SnippingCode.Domain.Code;
import SnippingCode.JsonParser.ParseJsonObject;
import SnippingCode.Service.CodesHttpRequest;
import SnippingCode.Service.FileOperation;
import SnippingCode.Service.UserHttpRequest;
import run.ExportCode;
import run.ImportCode;
import run.Login;
import run.SignUp;

/**
 * Our sample action implements workbench action delegate. The action proxy will
 * be created by the workbench and shown in the UI. When the user tries to use
 * the action, this delegate will be created and execution will be delegated to
 * it.
 * 
 * @see IWorkbenchWindowActionDelegate
 */
public class SampleAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	private Login login;
	private SignUp signUp;
	private ExportCode exportCode;
	private ImportCode importCode;
	private HashMap<String, String> user;

	/**
	 * The constructor.
	 */
	public SampleAction() {
		login = new Login();
		signUp = new SignUp();
		importCode = new ImportCode();
	}

	/**
	 * The action has been activated. The argument of the method represents the
	 * 'real' action sitting in the workbench UI.
	 * 
	 * @see IWorkbenchWindowActionDelegate#run
	 */

	public void run(IAction action) {

		switch (action.getId()) {
		case "login":
			login.run();

			break;
		case "signUp":
			signUp.run();
			break;
		case "import":
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IPath file = workspace.getRoot().getLocation();
			importCode.run(file.toString());
			break;
		case "export":
			IWorkspace workspace1 = ResourcesPlugin.getWorkspace();
			IPath file1 = workspace1.getRoot().getLocation();
			user = login.getUser();
			if (exportCode == null)
				exportCode = new ExportCode();
			exportCode.run(file1.toString(), user.get("username"),
					user.get("password"));
			break;
		default:
			break;
		}
	}

	/**
	 * Selection in the workbench has been changed. We can change the state of
	 * the 'real' action here if we want, but this can only happen after the
	 * delegate has been created.
	 * 
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system resources we previously
	 * allocated.
	 * 
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to be able to provide parent shell
	 * for the message dialog.
	 * 
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}