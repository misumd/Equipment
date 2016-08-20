package md.convertit.hydraulicsystem.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.GapContent;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import md.convertit.hydraulicsystem.domain.Equipment;
import md.convertit.hydraulicsystem.gui.model.SqlEquipmentTableModel;
import md.convertit.hydraulicsystem.services.FileService;
import md.convertit.hydraulicsystem.services.impl.JsonFileService;
import md.convertit.hydraulicsystem.services.impl.XmlFileService;
import md.convertit.hydraulicsystem.util.FileUtil;

public class TableFrame extends JFrame {
	private double newPrice = 1000; 
	
	private JPanel mainPanel;
	private JTextField nameTextField;
	private JTextField descriptionTextField;
	private JTextField tagTextField;
	private JFormattedTextField priceTextField;
	private JCheckBox stockCheckBox;
	
	private JButton saveButton;
	private JButton deleteButton;
	//private JButton editButton;
	private JButton exportJsonButton;
	private JButton exportXmlButton;
	private JTable table;
	private FileService fileService; 
	
	private static final long serialVersionUID = 1L;

	public TableFrame() throws HeadlessException {
		super();
		setTitle("Equipment Catalog");
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// init main panel with BorderLayout
		mainPanel = new JPanel(new BorderLayout());
		// add 'mainPanel' on frame
		getContentPane().add(mainPanel);

		// frame set minim size
		setMinimumSize(new Dimension(1000, 200));
	}

	/**
	 * Start method.
	 */
	public void start() {
		
	
	addTopPanel();
//		// add center panel
		addCenterPanel();
//		// add bottom panel
		addBottomPanel();
		
		addLefttPanel();
		
		addMenuBar();
//		// add action listeners
		addActionListeners();
//		// set visible
	setVisible(true);


	
	}

	private void addMenuBar() {
		JMenuBar menubar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F1);
		JMenu infoMenu = new JMenu("Info");
		infoMenu.setMnemonic(KeyEvent.VK_F2);

		JMenuItem addMenuItem = new JMenuItem("Add");
		addMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				saveEquipment();
						
			}
		});

		fileMenu.add(addMenuItem);
		
		JMenuItem clearMenuItem = new JMenuItem("Clear");
		clearMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clearEquipment();
				
			}

			
		});
		fileMenu.add(clearMenuItem);
		menubar.add(fileMenu);
		menubar.add(infoMenu);
		this.setJMenuBar(menubar);
	}
	
	private void saveEquipment() {
		// validate fields to not be empty
		boolean valid = validateFields();			
		if (valid) { // VALID
			// extract our UserTableModel from table
			Equipment equipment = new Equipment();
			equipment.setName(nameTextField.getText().trim());
			equipment.setDescription(descriptionTextField.getText().trim());
			equipment.setTag(tagTextField.getText().trim());
			equipment.setPrice(new Double(priceTextField.getText()));
			equipment.setInStock(stockCheckBox.isSelected());
		
			
			
			SqlEquipmentTableModel tableModel = (SqlEquipmentTableModel) table.getModel();
			
			// add user to tableModel
			tableModel.addEquipment(equipment);
			
			// clear fields
			nameTextField.setText("");
			descriptionTextField.setText("");
			tagTextField.setText("");
			priceTextField.setValue(0);
			stockCheckBox.isSelected();
		} else { // INVALID
			JOptionPane.showMessageDialog(TableFrame.this, 
					"Please check your fields!", "Validation Error", 
					JOptionPane.WARNING_MESSAGE);
		}
	}
	private void clearEquipment() {

		// obtain table selected row
		int row = table.getSelectedRow();
		// check if row value is different from -1  (no row selected)
		if(row != -1){
			// extract our UserTableModel from table
			SqlEquipmentTableModel tableModel = (SqlEquipmentTableModel) table.getModel();
			// obtain user from table model from selected row
			Equipment equipment = tableModel.getEquipment(row);
			// delete a user from table model
			tableModel.removeUser( equipment.getId().intValue());
		}else {
			JOptionPane.showMessageDialog(TableFrame.this, 
					"Please select a row from table!", "No selected row", 
					JOptionPane.WARNING_MESSAGE); 
		}
	
		
	}
	private void addActionListeners() {
		saveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				saveEquipment();
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				clearEquipment();
			}
		});

//		showButton.addActionListener(new ActionListener() {
//			
//		
//			public void actionPerformed(ActionEvent e) {
//				// Obtain selected user row
//				int row = table.getSelectedRow();
//				// obtain user from table model
//				if(row != -1){
//					SqlUserTableModel tableModel = (SqlUserTableModel) table.getModel();
//					// get user by row
//					User user = tableModel.getUser(row);
//					JOptionPane.showMessageDialog(TableFrame.this, 
//							user, "Selected user is:", 
//							JOptionPane.INFORMATION_MESSAGE); 
//				} else {
//					JOptionPane.showMessageDialog(TableFrame.this, 
//							"Please select a row from table!", "No selected row", 
//							JOptionPane.WARNING_MESSAGE); 
//				}
//			}
//		});
//		
		
		exportJsonButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				// extract table model from table
				SqlEquipmentTableModel tableModel = (SqlEquipmentTableModel) 
						table.getModel();
				// extract list of users from table model
				exportToJson(tableModel.getEquipments());
			}
		});
		
		exportXmlButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				// extract table model from table
				SqlEquipmentTableModel tableModel = (SqlEquipmentTableModel) 
						table.getModel();
				// extract list of users from table model
				exportToXml(tableModel.getEquipments());
			}
		});
	}
	 
	// Export users to JSON format
	private void exportToJson(List<Equipment> equipments) {
		// init fileService with concrete implementation of FileService 
		//(JsonFileService)
		fileService = new JsonFileService();
		try {
			// obtain file where to save
			String path = FileUtil.showSaveFileDialog();
			// check if a file was selected
			if (path == null) return;
			// save file to json
			fileService.saveAll(equipments, path.concat(".json"));
				JOptionPane.showMessageDialog(TableFrame.this, 
					"Equipments was successfully exported", "Export to JSON", 
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(TableFrame.this, 
					"Error on export to JSON", "Export to JSON", 
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	// Export users to XML format
		private void exportToXml(List<Equipment> equipments) {
			// init fileService with concrete implementation of FileService 
			//(XmlFileService)
			fileService = new XmlFileService();
			try {
				// obtain file where to save
				String path = FileUtil.showSaveFileDialog();
				// check if a file was selected
				if (path == null) return;
				fileService.saveAll(equipments, path.concat(".xml"));
				JOptionPane.showMessageDialog(TableFrame.this, 
						"Equipments was successfully exported", "Export to XML", 
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(TableFrame.this, 
						"Error on export to XML", "Export to XML", 
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}


	private void addBottomPanel() {
		JPanel panel = new JPanel();
		// init FlowLayout on LEFT
		
		FlowLayout layout = new FlowLayout(){

			@Override
			public Dimension preferredLayoutSize(Container target) {
				Dimension dim = super.preferredLayoutSize(target);
				dim.width = 130;
				// TODO Auto-generated method stub
				return dim;
			}
			
		};
		layout.setVgap(20);
		
		//FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
		// set layout to panel
		
		panel.setLayout(layout);
		
		
		// set border
		panel.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

		// init buttons and add to panel
		deleteButton = new JButton("Delete");
		saveButton = new JButton("Save");
		exportJsonButton = new JButton("Export to JSON");
		exportXmlButton = new JButton("Export to XML");
		panel.add(deleteButton);
		panel.add(saveButton);
		panel.add(exportJsonButton);
		panel.add(exportXmlButton);
		
		// add panel to mainPanel
		mainPanel.add(panel, BorderLayout.EAST);
		
	}

	private void addCenterPanel() {
	SqlEquipmentTableModel tableModel = new SqlEquipmentTableModel();
		
		// Init table
		table = new JTable(tableModel);
		
		// set selection mode to single
		table.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		
		// JScrollPane init
		JScrollPane scrollPane = new JScrollPane(table);

		// add scrollPane to mainPanel
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		
	}

	private void addTopPanel() {
		JPanel panel = new JPanel();
		// init FlowLayout on LEFT
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
		layout.setHgap(25);
		// set layout to panel
		panel.setLayout(layout);
		
		//panel.add(new JLabel("Equipments: "));
		//fileCombBox = new JComboBox<>();
		//panel.add(new JComboBox<>());

		// add label, init 'userNameTextField' and add to panel
		panel.add(new JLabel("Name: "));
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
		mainPanel.add(panel, BorderLayout.SOUTH);
		
	}
	private boolean validateFields() {
		// if any text filed is empty return false
		if (nameTextField.getText().trim().isEmpty() 
				|| descriptionTextField.getText().trim().isEmpty()
				|| tagTextField.getText().trim().isEmpty() || priceTextField.getText().trim().isEmpty()) {
			return false;
		}
		return true;
	}
	private void addLefttPanel() {
		JPanel panel1 = new JPanel();
		// init FlowLayout on LEFT
		
		FlowLayout layout1 = new FlowLayout(){

			@Override
			public Dimension preferredLayoutSize(Container target) {
				Dimension dim = super.preferredLayoutSize(target);
				dim.width = 130;
				// TODO Auto-generated method stub
				return dim;
			}
			
		};
		//layout1.setVgap(20);
		
		//FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
		// set layout to panel
		
		//panel1.setLayout(layout1);
		
		
		// set border
		panel1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
panel1.add(new JButton("12111"));

		// init buttons and add to panel
		
		
		// add panel to mainPanel
		mainPanel.add(panel1, BorderLayout.WEST);
		
	}
}
