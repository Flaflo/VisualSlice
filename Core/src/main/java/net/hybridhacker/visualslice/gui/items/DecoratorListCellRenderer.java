package net.hybridhacker.visualslice.gui.items;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Flaflo
 */
public class DecoratorListCellRenderer extends JPanel implements ListCellRenderer<DecoratorListItem> {

    private final JCheckBox checkBox = new JCheckBox();
    private final JButton button = new JButton("Config");

    public DecoratorListCellRenderer() {
        this.setLayout(new GridLayout(0, 2));
        this.add(checkBox);
        this.add(button);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends DecoratorListItem> list, DecoratorListItem value, int index, boolean isSelected, boolean cellHasFocus) {
        this.setForeground(list.getForeground());
        this.setBackground(list.getBackground().darker());
        
        checkBox.setEnabled(list.isEnabled());
        checkBox.setSelected(value.isSelected());
        checkBox.setFont(list.getFont());
        checkBox.setBackground(list.getBackground());
        checkBox.setForeground(list.getForeground());
        checkBox.setText(value.toString());

        button.setEnabled(value.isConfigurable());
        button.setFont(list.getFont());
        button.setBackground(list.getBackground());
        button.setForeground(list.getForeground());

        return this;
    }
}
