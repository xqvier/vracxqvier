package com.xqvier.test.ihm.panel;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.xqvier.test.ihm.component.MyCellRenderer;

public class TablePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public TablePanel() {
		JTable table = new JTable();
		this.add(table);
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		for(int i = 0; i < 6 ; i++){
			model.addColumn("Colonne " + i);
		}
		
		for(int i = 0 ; i < 10 ; i++){
			String test = i % 2 == 0 ? "" : i + ".5";
			model.addRow(new Object[]{i + ".0", i + ".1", i + ".2",i + ".3",i + ".4",test});
		}
		
		table.setDefaultRenderer(Object.class, new MyCellRenderer());
		
		
	}

}
