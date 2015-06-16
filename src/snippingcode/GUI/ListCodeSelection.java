package snippingcode.GUI;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import snippingcode.domain.UserDomain;
import snippingcode.service.ClipboardCopy;

import java.lang.reflect.Constructor;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class ListCodeSelection extends JPanel {
	private JTextArea output;
	private JList list;
	private JTable table;
	private String newline = "\n";
	private ListSelectionModel listSelectionModel;
	private String[] listData;
	private JButton copyCode;
	private JPanel controlPane;
	private int counter = 0;
	private JFrame frame;

	public void createDataList() {
		int length = UserDomain.getSize();
		listData = new String[length];
		for (int i = 0; i < length; i++) {
			listData[i] = UserDomain.getCodeDomain(i).getName();
		}
	}

	public void createListGUI() {
		list = new JList(listData);

		listSelectionModel = list.getSelectionModel();
		listSelectionModel
				.addListSelectionListener(new SharedListSelectionHandler());
		JScrollPane listPane = new JScrollPane(list);

		controlPane = new JPanel();

		copyCode = new JButton();
		copyCode.setText("Copy Code");
		controlPane.add(copyCode);

		// Build output area.
		output = new JTextArea(1, 10);
		output.setEditable(false);
		JScrollPane outputPane = new JScrollPane(output,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		// Do the layout.
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);

		JPanel topHalf = new JPanel();
		topHalf.setLayout(new BoxLayout(topHalf, BoxLayout.LINE_AXIS));
		JPanel listContainer = new JPanel(new GridLayout(1, 1));
		listContainer.setBorder(BorderFactory.createTitledBorder("List"));
		listContainer.add(listPane);

		topHalf.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
		topHalf.add(listContainer);
		// topHalf.add(tableContainer);

		topHalf.setMinimumSize(new Dimension(100, 50));
		topHalf.setPreferredSize(new Dimension(100, 110));
		splitPane.add(topHalf);

		JPanel bottomHalf = new JPanel(new BorderLayout());
		bottomHalf.add(controlPane, BorderLayout.PAGE_START);
		bottomHalf.add(outputPane, BorderLayout.CENTER);
		bottomHalf.setPreferredSize(new Dimension(500, 500));
		splitPane.add(bottomHalf);

	}

	public void onclickListner() {
		copyCode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("copyCoe");
				if (list.getSelectedIndex() == -1)
					return;
				ClipboardCopy.copy(UserDomain.getCodeDomain(
						list.getSelectedIndex()).getCode());
				frame.setVisible(false);
			}
		});
	}

	public ListCodeSelection() {
		super(new BorderLayout());
	}

	private void setWindowPosition() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int width = frame.getWidth();
		int height = frame.getHeight();
		int x = (int) ((dimension.getWidth() - width) / 2);
		int y = (int) ((dimension.getHeight() - height) / 2);
		frame.setLocation(x, y);
	}

	public void createAndShowGUI() {
		frame = new JFrame("listCode");

		this.setOpaque(true);
		frame.setContentPane(this);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	class SharedListSelectionHandler implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting() || e.getFirstIndex() == -1
					|| list.getSelectedIndex() == -1)
				return;
			int firstIndex = list.getSelectedIndex();
			output.setText(UserDomain.getCodeDomain(firstIndex).getCode());
			output.setCaretPosition(output.getDocument().getLength());
		}
	}

	public void Run(Boolean check) {
		if (check) {
			createDataList();
			createListGUI();
			createAndShowGUI();
			setWindowPosition();
			onclickListner();
		}
	}
}
