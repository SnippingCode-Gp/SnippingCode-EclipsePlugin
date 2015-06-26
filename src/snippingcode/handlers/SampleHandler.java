package snippingcode.handlers;

import java.awt.Frame;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JFrame;

import org.apache.http.client.ClientProtocolException;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.json.JSONException;

import snippingcode.GUI.ListCodeSelection;
import snippingcode.GUI.LoginGUI;
import snippingcode.HttpRequest.GetCodeHttp;
import snippingcode.HttpRequest.LoginHttpRequest;
import snippingcode.controller.LoginController;
import snippingcode.domain.UserDomain;

public class SampleHandler extends AbstractHandler {

	private LoginGUI gUILogin;
	private ListCodeSelection listCode;
	private LoginController loginctr;
	private GetCodeHttp getCodeHttp;

	public SampleHandler() {
		loginctr = new LoginController();
		getCodeHttp = new GetCodeHttp();
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);

		gUILogin = new LoginGUI();
		listCode = new ListCodeSelection();

		if (!loginctr.checkCache()) {
			UserDomain.parseString();
			gUILogin.Run(true);
		}
		
		if (!UserDomain.getUsername().equals("") && loginctr.checkCache()) {
			UserDomain.clearList();
			try {
				getCodeHttp.getCode(UserDomain.getUsername() , UserDomain.getPassword());
			} catch (ClientProtocolException e) {
				System.out.println(e.toString());
			} catch (IOException e) {
				System.out.println(e.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listCode.Run(true);
			System.out.println("Done loading");
		}

		return null;
	}
}
