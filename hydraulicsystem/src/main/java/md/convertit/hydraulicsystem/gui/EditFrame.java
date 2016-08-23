package md.convertit.hydraulicsystem.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;

import org.junit.runners.model.TestTimedOutException;

import md.convertit.hydraulicsystem.domain.Equipment;
import md.convertit.hydraulicsystem.gui.model.SqlEquipmentTableModel;

public class EditFrame extends JFrame {
	private JPanel editPanel;
	private JTable table;
	private JTextField nameTextField;
	private JTextField descriptionTextField;
	private JTextField tagTextField;
	private JFormattedTextField priceTextField;
	private JCheckBox stockCheckBox;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EditFrame() throws HeadlessException {
		super();
		setTitle("Edit Equipment");
		setSize(600, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// init main panel with BorderLayout
		editPanel = new JPanel(new BorderLayout());
		// add 'mainPanel' on frame
		getContentPane().add(editPanel);

		// frame set minim size
		//setMinimumSize(new Dimension(1000, 200));
	}

	public void start() {
		addActionListeners();
		setVisible(true);
		addLeftPanel();
		addBottomPanel();
		addRightPanel();
	}
	
	private void addRightPanel() {
		 
	 	
	
	}

	private void addBottomPanel() {
		JPanel panel5 = new JPanel();
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
		layout.setHgap(70);
		panel5.setLayout(layout);
		JButton addSymbol = new JButton("Add symbol");
		panel5.add(addSymbol);
		
		JButton saveSymbol = new JButton("Save symbol");
		panel5.add(saveSymbol);
		
		JButton deleteSymbol = new JButton("Delete symbol");
		panel5.add(deleteSymbol);
		
		panel5.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        editPanel.add(panel5, BorderLayout.SOUTH);
	}

	private void addLeftPanel() {
		JPanel panel = new JPanel();
		// init FlowLayout on LEFT
		
		FlowLayout layout = new FlowLayout(){

			@Override
			public Dimension preferredLayoutSize(Container target) {
				Dimension dim = super.preferredLayoutSize(target);
				dim.width = 150;
				// TODO Auto-generated method stub
				return dim;
			}
			
		};
		layout.setVgap(30);
		// set layout to panel
		panel.setLayout(layout);
		
		//panel.add(new JLabel("Equipments: "));
		//fileCombBox = new JComboBox<>();
		//panel.add(new JComboBox<>());

		// add label, init 'userNameTextField' and add to panel
		JLabel nameLabel = new JLabel("Name: ");
		panel.add(nameLabel);
		nameTextField = new JTextField(10);
		panel.add(nameTextField);
		
		

		// add label, init 'passwordField' and add to panel
		panel.add(new JLabel("Description: "));
		descriptionTextField = new JTextField(10);
		panel.add(descriptionTextField);

		// add label, init 'emailTextField' and add to panel
		panel.add(new JLabel("Tag: "));
		tagTextField = new JTextField(10);
		panel.add(tagTextField);
		
		panel.add(new JLabel("Price: "));
		priceTextField = new JFormattedTextField(new Float(0.0));
		priceTextField.setMinimumSize(priceTextField.getPreferredSize());
		priceTextField.setColumns(10);
		panel.add(priceTextField);
		
		panel.add(new JLabel("Is_inStock: "));
		stockCheckBox = new JCheckBox();
		panel.add(stockCheckBox);

		
		//newButton = new JButton("New");
		//panel.add(newButton);

		// panel add border
		panel.setBorder(new EtchedBorder());

		// add panel to mainPanel
		editPanel.add(panel, BorderLayout.WEST);
		
	}

	private void addActionListeners() {

		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Obtain selected user row
				int row = table.getSelectedRow();
				// obtain user from table model
				if (row != -1) {
					SqlEquipmentTableModel tableModel7 = (SqlEquipmentTableModel) table.getModel();
					// get user by row
					Equipment equipment = tableModel7.getEquipment(row);
					JOptionPane.showMessageDialog(EditFrame.this, equipment, "Selected user is:",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(EditFrame.this, "Please select a row from table!", "No selected row",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

	}

}
