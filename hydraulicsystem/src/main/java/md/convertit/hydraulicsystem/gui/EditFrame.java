package md.convertit.hydraulicsystem.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.List;

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

import org.apache.poi.ss.formula.functions.Column;
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
	private JLabel nameLabel;
	private JLabel descriptionLabel;
	private JLabel tagLabel;
	private JLabel priceLabel;
	private JLabel isInStockLabel;
	private JLabel idLabel;
	private JTextField idTextField;
	private int Id;
	private int name;
	private String Name;
	private JButton showButton;
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
		layout.setHgap(0);
		panel5.setLayout(layout);
		JButton addSymbol = new JButton("Add symbol");
		panel5.add(addSymbol);
		
		JButton saveSymbol = new JButton("Save symbol");
		panel5.add(saveSymbol);
		
		JButton deleteSymbol = new JButton("Delete symbol");
		panel5.add(deleteSymbol);
		
		//showButton = new JButton("Open equipment");
		//panel5.add(showButton);
		
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
		
		
		
		
		
		idLabel = new JLabel("ID: ");
		panel.add(idLabel);
		idTextField = new JTextField(10);
		panel.add(idTextField);
		// add label, init 'userNameTextField' and add to panel
		
		nameLabel = new JLabel("Name: ");
		panel.add(nameLabel);
		String Name1 = getName();
		nameTextField = new JTextField(Name1);
		panel.add(nameTextField);
		
		

		// add label, init 'passwordField' and add to panel
		descriptionLabel= new JLabel("Description: ");
		panel.add(descriptionLabel);
		descriptionTextField = new JTextField(10);
		panel.add(descriptionTextField);

		// add label, init 'emailTextField' and add to panel
		tagLabel = new JLabel("Tag: ");
		panel.add(tagLabel);
		tagTextField = new JTextField(10);
		panel.add(tagTextField);
		
		priceLabel = new JLabel("Price: ");
		panel.add(priceLabel);
		
		priceTextField = new JFormattedTextField(new Float(0.0));
		priceTextField.setMinimumSize(priceTextField.getPreferredSize());
		priceTextField.setColumns(10);
		panel.add(priceTextField);
		
		isInStockLabel = new JLabel("Is_inStock: ");
		panel.add(isInStockLabel);
		stockCheckBox = new JCheckBox();
		panel.add(stockCheckBox);

		
		//newButton = new JButton("New");
		//panel.add(newButton);

		// panel add border
		panel.setBorder(new EtchedBorder());

		// add panel to mainPanel
		editPanel.add(panel, BorderLayout.WEST);
		
	}
 
public String getName() {
	
	
	 
	SqlEquipmentTableModel tableModel1 = new SqlEquipmentTableModel();
	
		int row1 = tableModel1.getEquipments().get(Id).getId().intValue();
		Equipment equipment = tableModel1.getEquipment(row1);
		
			 //equipment.getPath_symbols();
		
		
				
				Name = equipment.getName();
				return Name;
		
			
			
			
	}
	
	private void addActionListeners() {
		
	}}
