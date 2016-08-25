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
import javax.swing.table.TableModel;

import org.apache.poi.ss.formula.functions.Column;
import org.junit.runners.model.TestTimedOutException;

import com.mysql.fabric.xmlrpc.base.Value;

import md.convertit.hydraulicsystem.dao.EquipmentDao;
import md.convertit.hydraulicsystem.dao.impl.EquipmentDaoImpl;
import md.convertit.hydraulicsystem.domain.Equipment;
import md.convertit.hydraulicsystem.gui.model.SqlEquipmentTableModel;

public class EditFrame extends JFrame {

	private Equipment equipment;
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
	private JLabel pathLabel;
	private JTextField pathTextField;
	private JButton saveSymbol;
	private JButton deleteSymbol;
	private JButton addSymbol;
	private SqlEquipmentTableModel tableModel;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EditFrame(Equipment equipment) throws HeadlessException {
		super();
		this.equipment = equipment;
		setTitle("Edit Equipment");
		setSize(600, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// init main panel with BorderLayout
		editPanel = new JPanel(new BorderLayout());
		// add 'mainPanel' on frame
		getContentPane().add(editPanel);

		// frame set minim size
		 setMinimumSize(new Dimension(500, 200));
	}

	public void start() {
		addActionListeners();
		setVisible(true);
		addLeftPanel();
		addBottomPanel();
		addRightPanel();
		addCenterPanel();
	}

	private void addCenterPanel() {
		
		ImageIcon myImage = new ImageIcon(equipment.getPath_symbols());
        ImagePanel panel5 = new ImagePanel(myImage.getImage());
        editPanel.add(panel5, BorderLayout.CENTER);
	}

	private void addRightPanel() {

	}

	private void addBottomPanel() {
		JPanel panel5 = new JPanel();
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
		layout.setHgap(10);
		panel5.setLayout(layout);
		
		addSymbol = new JButton("Add symbol");
		panel5.add(addSymbol);

		saveSymbol = new JButton("Save");
		panel5.add(saveSymbol);

		deleteSymbol = new JButton("Delete symbol");
		panel5.add(deleteSymbol);

		// showButton = new JButton("Open equipment");
		// panel5.add(showButton);

		panel5.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		editPanel.add(panel5, BorderLayout.SOUTH);
	}

	private void addLeftPanel() {
		JPanel panel = new JPanel();
		// init FlowLayout on LEFT

		FlowLayout layout = new FlowLayout() {

			@Override
			public Dimension preferredLayoutSize(Container target) {
				Dimension dim = super.preferredLayoutSize(target);
				dim.width = 150;
				// TODO Auto-generated method stub
				return dim;
			}

		};
		layout.setVgap(10);
		// set layout to panel
		panel.setLayout(layout);

		idLabel = new JLabel("ID: ");
		panel.add(idLabel);
		idTextField = new JTextField(10);
		idTextField.setText(String.valueOf(equipment.getId()));
		idTextField.setEditable(false);
		panel.add(idTextField);
		// add label, init 'userNameTextField' and add to panel

		nameLabel = new JLabel("Name: ");
		panel.add(nameLabel);
		String Name1 = getName();
		nameTextField = new JTextField(10);
		nameTextField.setText(equipment.getName());
		
		panel.add(nameTextField);

		// add label, init 'passwordField' and add to panel
		descriptionLabel = new JLabel("Description: ");
		panel.add(descriptionLabel);
		descriptionTextField = new JTextField(10);
		descriptionTextField.setText(equipment.getDescription());
		panel.add(descriptionTextField);
		

		// add label, init 'emailTextField' and add to panel
		tagLabel = new JLabel("Tag: ");
		panel.add(tagLabel);
		tagTextField = new JTextField(10);
		tagTextField.setText(equipment.getTag());
		panel.add(tagTextField);

		priceLabel = new JLabel("Price: ");
		panel.add(priceLabel);

		priceTextField = new JFormattedTextField(new Float(0.0));
		priceTextField.setMinimumSize(priceTextField.getPreferredSize());
		priceTextField.setColumns(10);
		priceTextField.setText(String.valueOf(equipment.getPrice()));
		panel.add(priceTextField);
		

		isInStockLabel = new JLabel("Is_inStock: ");
		panel.add(isInStockLabel);
		stockCheckBox = new JCheckBox();
		stockCheckBox.setSelected(equipment.isInStock());
		//stockCheckBox.setText(String.valueOf(equipment.isInStock()));
		panel.add(stockCheckBox);

		// newButton = new JButton("New");
		// panel.add(newButton);
		pathLabel = new JLabel("Path_Symbol: ");
		panel.add(pathLabel);
		pathTextField = new JTextField(10);
		pathTextField.setText(equipment.getPath_symbols());
		pathTextField.setEditable(false);
		panel.add(pathTextField);
		
		// panel add border
		panel.setBorder(new EtchedBorder());

		// add panel to mainPanel
		editPanel.add(panel, BorderLayout.WEST);

	}
	private void saveSymbol() {
		
		Long id = Long.valueOf(idTextField.getText());
		
		equipment.setName(nameTextField.getText().trim());
		
		equipment.setDescription(descriptionTextField.getText().trim());
		
		equipment.setTag(tagTextField.getText().trim());
		
		equipment.setPrice(new Double(priceTextField.getText()));
		
		equipment.setInStock(stockCheckBox.isSelected());
		
		//

		// add user to tableModel
		SqlEquipmentTableModel tableModel4 = (SqlEquipmentTableModel) table.getModel();
		
		tableModel4.updateEquipment(equipment, id);
		
		
		
	}
//	private boolean validateFields() {
//		// if any text filed is empty return false
//		if (nameTextField.getText().trim().isEmpty() || descriptionTextField.getText().trim().isEmpty()
//				|| tagTextField.getText().trim().isEmpty() || priceTextField.getText().trim().isEmpty()) {
//			return false;
//		}
//		return true;
	//}
	private void addActionListeners() {
		
//		saveSymbol.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				saveSymbol();
//				
//			}
//		});

	}
}
