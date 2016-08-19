package md.convertit.hydraulicsystem.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	private JPanel mainPanel;
	private JTextField nameTextField;
	private JTextField descriptionTextField;
	private JTextField tagTextField;
	private JTextField priceTextField;
	private JTextField stockTextField;
	
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
		setSize(800, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// init main panel with BorderLayout
		mainPanel = new JPanel(new BorderLayout());
		// add 'mainPanel' on frame
		getContentPane().add(mainPanel);

		// frame set minim size
		setMinimumSize(new Dimension(800, 200));
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
//		// add action listeners
		addActionListeners();
//		// set visible
	setVisible(true);


	
	}

	private void addActionListeners() {
saveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// validate fields to not be empty
				boolean valid = validateFields();			
				if (valid) { // VALID
					// extract our UserTableModel from table
					Equipment equipment = new Equipment();
					equipment.setName(nameTextField.getText().trim());
					equipment.setDescription(descriptionTextField.getText().trim());
					equipment.setTag(tagTextField.getText().trim());
					equipment.setPrice(priceTextField.getText().trim());
					equipment.setInStock(stockTextField.getText().trim());
					
					
					
					SqlEquipmentTableModel tableModel = (SqlEquipmentTableModel) table.getModel();
					
					// add user to tableModel
					tableModel.addEquipment(equipment);
					
					// clear fields
					nameTextField.setText("");
					descriptionTextField.setText("");
					tagTextField.setText("");
					priceTextField.setText("");
					stockTextField.setEditable(valid);
				} else { // INVALID
					JOptionPane.showMessageDialog(TableFrame.this, 
							"Please check your fields!", "Validation Error", 
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
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
		
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
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
		priceTextField = new JTextField(10);
		panel.add(priceTextField);
		
		panel.add(new JLabel("Is_inStock: "));
		stockTextField = new JTextField(10);
		panel.add(stockTextField);

		
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
}
