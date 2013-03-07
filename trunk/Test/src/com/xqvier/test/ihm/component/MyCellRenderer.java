package com.xqvier.test.ihm.component;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		
		if(column == 5 && value.equals("")){
			cell.setBackground(Color.GREEN);
		} else {
			cell.setBackground(Color.WHITE);
		}
		return cell;
	}
	
}
