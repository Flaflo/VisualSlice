package net.hybridhacker.visualslice.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.hybridhacker.visualslice.gui.items.DecoratorListCellRenderer;
import net.hybridhacker.visualslice.gui.items.DecoratorListItem;
import net.hybridhacker.visualslice.gui.screens.settings.DecoratorSettingsFrame;
import net.hybridhacker.visualslice.renderer.VisualizerRenderer;
import net.hybridhacker.visualslice.visualizer.DecoratorRegistry;
import net.hybridhacker.visualslice.visualizer.VisualizerRegistry;

/**
 * @author Flaflo
 */
public class VisualSliceGui extends javax.swing.JPanel {

    /**
     * Creates new form VisualSliceGui
     */
    public VisualSliceGui() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        initComponents();

        this.decoratorList.setModel(new AbstractListModel<DecoratorListItem>() {
            DecoratorListItem[] items;

            @Override
            public int getSize() {
                return items.length;
            }

            @Override
            public DecoratorListItem getElementAt(int index) {
                return items[index];
            }

            public AbstractListModel<DecoratorListItem> init() {
                final String[] decorators = DecoratorRegistry.getInstance().getRegisteredDecorators();
                items = new DecoratorListItem[decorators.length];
                for (int i = 0; i < items.length; i++) {
                    items[i] = new DecoratorListItem(decorators[i], true);
                }
                return this;
            }
        }.init());
        this.decoratorList.setCellRenderer(new DecoratorListCellRenderer());

        this.decoratorList.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    final JList<DecoratorListItem> list = (JList<DecoratorListItem>) e.getSource();
                    final int index = list.locationToIndex(e.getPoint());

                    final DecoratorListItem item = (DecoratorListItem) list.getModel().getElementAt(index);
                    final String decorator = DecoratorRegistry.getInstance().getRegisteredDecorators()[index];

                    if (e.getX() >= decoratorList.getWidth() / 4 && e.getX() >= decoratorList.getWidth() / 2) {
                        if (DecoratorRegistry.getInstance().getSettingsOfDecorator(decorator).length > 0) {
                            final DecoratorSettingsFrame settings = new DecoratorSettingsFrame(Controller.getInstance().getDisplay().getWindow(), decorator);
                            settings.pack();
                            settings.setVisible(true);
                        }
                    } else {
                        item.setSelected(!item.isSelected());
                        if (item.isSelected()) {
                            Controller.getInstance().getBuilder().addDecorator(decorator);
                        } else {
                            Controller.getInstance().getBuilder().removeDecorator(decorator);
                        }
                        list.repaint(list.getCellBounds(index, index));
                    }
                }
            }
        });

        Arrays.stream(VisualizerRegistry.getInstance().getRegisteredVisualizers()).forEach(string -> this.visualizerCombobox.addItem(string));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        decoratorList = new javax.swing.JList<>();
        applyButton = new javax.swing.JButton();
        visualizerCombobox = new javax.swing.JComboBox<>();
        musicTextfield = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        musicLabel = new javax.swing.JLabel();
        playButton = new javax.swing.JButton();
        visualizerLabel = new javax.swing.JLabel();

        decoratorList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(decoratorList);

        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        visualizerCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizerComboboxActionPerformed(evt);
            }
        });

        musicTextfield.setEditable(false);

        browseButton.setText("Browse");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        musicLabel.setText("Music:");

        playButton.setText("Play");
        playButton.setEnabled(false);
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        visualizerLabel.setText("Visualizer:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(visualizerLabel)
                            .addComponent(musicLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(visualizerCombobox, 0, 341, Short.MAX_VALUE)
                            .addComponent(musicTextfield))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(browseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(applyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(playButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(visualizerCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(visualizerLabel)
                    .addComponent(applyButton))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(musicTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(musicLabel)
                    .addComponent(browseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Called when the apply button was pressed
     *
     * @param evt the event
     */
    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        for (final Runnable runnable : Controller.getInstance().getDisplay().getRenderers()) {
            Controller.getInstance().getDisplay().removeRenderer(runnable);
        }

        Controller.getInstance().getDisplay().addRenderer(
                new VisualizerRenderer(Controller.getInstance().getBuilder().buildVisualizer(),
                        Controller.getInstance().getPlayer()));
    }//GEN-LAST:event_applyButtonActionPerformed

    /**
     * Called when the browse button was pressed
     *
     * @param evt the event
     */
    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        final JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("MP3 files", "mp3"));

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            final String destination = chooser.getSelectedFile().getAbsolutePath();
            this.musicTextfield.setText(destination);

            this.playButton.setEnabled(true);
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    /**
     * Called when the play button is pressed
     *
     * @param evt the event
     */
    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        Controller.getInstance().getPlayer().play(new File(this.musicTextfield.getText()).toURI());
    }//GEN-LAST:event_playButtonActionPerformed

    /**
     * Called when the combobox item was changed
     *
     * @param evt the event
     */
    private void visualizerComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualizerComboboxActionPerformed
        Controller.getInstance().getBuilder().setVisulizerName(this.visualizerCombobox.getItemAt(this.visualizerCombobox.getSelectedIndex()));
    }//GEN-LAST:event_visualizerComboboxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyButton;
    private javax.swing.JButton browseButton;
    private javax.swing.JList<net.hybridhacker.visualslice.gui.items.DecoratorListItem> decoratorList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel musicLabel;
    private javax.swing.JTextField musicTextfield;
    private javax.swing.JButton playButton;
    private javax.swing.JComboBox<String> visualizerCombobox;
    private javax.swing.JLabel visualizerLabel;
    // End of variables declaration//GEN-END:variables
}
