package com.tonikelope.megabasterd;

import static com.tonikelope.megabasterd.MainPanel.*;
import static com.tonikelope.megabasterd.MiscTools.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author tonikelope
 */
public final class LinkGrabberDialog extends javax.swing.JDialog implements ClipboardChangeObserver {

    private boolean _download;
    private String _download_path, _selected_item;
    private final ClipboardSpy _clipboardspy;
    private final MainPanel _main_panel;

    public MainPanel getMain_panel() {
        return _main_panel;
    }

    public JComboBox<String> getUse_mega_account_down_combobox() {
        return use_mega_account_down_combobox;
    }

    public JButton getDance_button() {
        return dance_button;
    }

    public boolean isDownload() {
        return _download;
    }

    public String getDownload_path() {
        return _download_path;
    }

    public JTextArea getLinks_textarea() {
        return links_textarea;
    }

    public LinkGrabberDialog(MainPanelView parent, boolean modal, String download_path, ClipboardSpy clipboardspy) {

        super(parent, modal);

        _main_panel = parent.getMain_panel();

        initComponents();

        updateFonts(this, GUI_FONT, _main_panel.getZoom_factor());

        translateLabels(this);

        _download = false;

        _download_path = Paths.get(download_path).toAbsolutePath().normalize().toString();

        _selected_item = null;

        _clipboardspy = clipboardspy;

        download_dir_label.setText(truncateText(_download_path, 80));

        if (_main_panel.isUse_mega_account_down() && _main_panel.getMega_accounts().size() > 0) {

            THREAD_POOL.execute(new Runnable() {
                @Override
                public void run() {

                    swingInvoke(new Runnable() {
                        @Override
                        public void run() {

                            String mega_default_down = _main_panel.getMega_account_down();

                            use_mega_account_down_combobox.addItem(mega_default_down);

                            for (Object k : _main_panel.getMega_accounts().keySet()) {

                                if (!mega_default_down.equals(k)) {
                                    use_mega_account_down_combobox.addItem((String) k);
                                }

                            }

                            use_mega_account_down_combobox.addItem("");
                            use_mega_account_down_combobox.setSelectedIndex(0);
                        }
                    });

                }
            });

        } else {
            use_mega_account_down_combobox.setEnabled(false);
            use_mega_account_down_combobox.setVisible(false);
            use_mega_account_down_label.setEnabled(false);
            use_mega_account_down_label.setVisible(false);
        }

        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        links_scrollpane = new javax.swing.JScrollPane();
        links_textarea = new javax.swing.JTextArea();
        dance_button = new javax.swing.JButton();
        links_label = new javax.swing.JLabel();
        change_dir_button = new javax.swing.JButton();
        down_dir_to_label = new javax.swing.JLabel();
        download_dir_label = new javax.swing.JLabel();
        dlc_button = new javax.swing.JButton();
        use_mega_account_down_label = new javax.swing.JLabel();
        use_mega_account_down_combobox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Link Grabber");

        links_textarea.setColumns(20);
        links_textarea.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        links_textarea.setRows(5);
        links_textarea.setDoubleBuffered(true);
        links_scrollpane.setViewportView(links_textarea);
        links_textarea.addMouseListener(new ContextMenuMouseListener());

        dance_button.setBackground(new java.awt.Color(102, 204, 255));
        dance_button.setFont(new java.awt.Font("Dialog", 1, 22)); // NOI18N
        dance_button.setForeground(new java.awt.Color(255, 255, 255));
        dance_button.setText("Let's dance, baby");
        dance_button.setDoubleBuffered(true);
        dance_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dance_buttonActionPerformed(evt);
            }
        });

        links_label.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        links_label.setText("Put your MEGA/MegaCrypter/ELC link/s here (one per line):");
        links_label.setDoubleBuffered(true);

        change_dir_button.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        change_dir_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-folder-30.png"))); // NOI18N
        change_dir_button.setText("Change it");
        change_dir_button.setDoubleBuffered(true);
        change_dir_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                change_dir_buttonActionPerformed(evt);
            }
        });

        down_dir_to_label.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        down_dir_to_label.setText("Download folder:");
        down_dir_to_label.setDoubleBuffered(true);

        download_dir_label.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        download_dir_label.setText("default dir");

        dlc_button.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        dlc_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-add-file-30.png"))); // NOI18N
        dlc_button.setText("Load DLC container");
        dlc_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dlc_buttonActionPerformed(evt);
            }
        });

        use_mega_account_down_label.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        use_mega_account_down_label.setText("Use this account for download:");

        use_mega_account_down_combobox.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        use_mega_account_down_combobox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                use_mega_account_down_comboboxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 746, Short.MAX_VALUE)
                        .addComponent(dance_button))
                    .addComponent(links_scrollpane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(links_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                        .addComponent(dlc_button))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(use_mega_account_down_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(use_mega_account_down_combobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(change_dir_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(down_dir_to_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(download_dir_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(links_label)
                    .addComponent(dlc_button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(links_scrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(change_dir_button)
                    .addComponent(down_dir_to_label)
                    .addComponent(download_dir_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(use_mega_account_down_label)
                    .addComponent(use_mega_account_down_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dance_button)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dance_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dance_buttonActionPerformed

        _download = true;

        setVisible(false);
    }//GEN-LAST:event_dance_buttonActionPerformed

    private void change_dir_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_change_dir_buttonActionPerformed

        change_dir_button.setText(LabelTranslatorSingleton.getInstance().translate("Selecting folder..."));
        change_dir_button.setEnabled(false);

        javax.swing.JFileChooser filechooser = new javax.swing.JFileChooser();

        updateFonts(filechooser, GUI_FONT, (float) (_main_panel.getZoom_factor() * 1.25));

        filechooser.setCurrentDirectory(new java.io.File(_download_path));
        filechooser.setDialogTitle("Download folder");
        filechooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        filechooser.setAcceptAllFileFilterUsed(false);

        if (filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            File file = filechooser.getSelectedFile();

            _download_path = file.getAbsolutePath();

            download_dir_label.setText(truncateText(_download_path, 80));

        }

        change_dir_button.setText(LabelTranslatorSingleton.getInstance().translate("Change it"));
        change_dir_button.setEnabled(true);

        pack();
    }//GEN-LAST:event_change_dir_buttonActionPerformed

    private void dlc_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dlc_buttonActionPerformed

        dlc_button.setText(LabelTranslatorSingleton.getInstance().translate("Loading DLC, please wait..."));

        dlc_button.setEnabled(false);

        links_textarea.setEnabled(false);

        dance_button.setEnabled(false);

        pack();

        javax.swing.JFileChooser filechooser = new javax.swing.JFileChooser();

        updateFonts(filechooser, GUI_FONT, (float) (_main_panel.getZoom_factor() * 1.25));

        filechooser.setDialogTitle("Select DLC container");

        filechooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);

        filechooser.addChoosableFileFilter(new FileNameExtensionFilter("DLC", "dlc"));

        filechooser.setAcceptAllFileFilterUsed(false);

        if (filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            final File file = filechooser.getSelectedFile();

            THREAD_POOL.execute(new Runnable() {
                @Override
                public void run() {

                    try (FileInputStream is = new FileInputStream(file); ByteArrayOutputStream out = new ByteArrayOutputStream()) {

                        byte[] buffer = new byte[MainPanel.DEFAULT_BYTE_BUFFER_SIZE];

                        int reads;

                        while ((reads = is.read(buffer)) != -1) {

                            out.write(buffer, 0, reads);
                        }

                        String dlc = new String(out.toByteArray(), "UTF-8");

                        Set<String> links = CryptTools.decryptDLC(dlc, ((MainPanelView) getParent()).getMain_panel());

                        for (Iterator<String> i = links.iterator(); i.hasNext();) {

                            String link = i.next();

                            if (findFirstRegex("(?:https?|mega)://[^/]*/(#.*?)?!.+![^\r\n]+", link, 0) == null) {

                                i.remove();
                            }
                        }

                        if (!links.isEmpty()) {

                            swingInvoke(new Runnable() {
                                @Override
                                public void run() {
                                    links_textarea.setText("");

                                    for (Iterator<String> i = links.iterator(); i.hasNext();) {

                                        links_textarea.append(i.next());

                                        if (i.hasNext()) {

                                            links_textarea.append("\r\n");
                                        }
                                    }
                                }
                            });

                        }

                    } catch (FileNotFoundException ex) {
                        LOG.log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        LOG.log(Level.SEVERE, null, ex);
                    }

                    swingInvoke(new Runnable() {
                        @Override
                        public void run() {
                            dlc_button.setText(LabelTranslatorSingleton.getInstance().translate("Load DLC container"));

                            dlc_button.setEnabled(true);

                            links_textarea.setEnabled(true);

                            dance_button.setEnabled(true);

                            pack();
                        }
                    });

                }
            });

        } else {

            dlc_button.setText(LabelTranslatorSingleton.getInstance().translate("Load DLC container"));

            dlc_button.setEnabled(true);

            links_textarea.setEnabled(true);

            dance_button.setEnabled(true);

            pack();

        }
    }//GEN-LAST:event_dlc_buttonActionPerformed

    private void use_mega_account_down_comboboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_use_mega_account_down_comboboxItemStateChanged

        if (_selected_item == null || !use_mega_account_down_combobox.getSelectedItem().equals(_selected_item)) {
            _selected_item = (String) use_mega_account_down_combobox.getSelectedItem();

            if (_main_panel.isUse_mega_account_down() && !"".equals(_selected_item)) {

                use_mega_account_down_combobox.setEnabled(false);

                dance_button.setEnabled(false);

                dance_button.setText(LabelTranslatorSingleton.getInstance().translate("Checking MEGA account..."));

                pack();

                final LinkGrabberDialog tthis = this;

                THREAD_POOL.execute(new Runnable() {
                    @Override
                    public void run() {

                        boolean use_account = true;

                        try {

                            if (checkMegaAccountLoginAndShowMasterPassDialog(_main_panel, tthis, _selected_item) == null) {
                                use_account = false;
                            }

                        } catch (Exception ex) {

                            use_account = false;
                        }

                        if (!use_account) {
                            swingInvoke(new Runnable() {
                                @Override
                                public void run() {
                                    use_mega_account_down_combobox.setSelectedIndex(_main_panel.getMega_accounts().size());

                                }
                            });
                        }

                        swingInvoke(new Runnable() {
                            @Override
                            public void run() {
                                getUse_mega_account_down_combobox().setEnabled(true);

                                getDance_button().setText(LabelTranslatorSingleton.getInstance().translate("Let's dance, baby"));

                                getDance_button().setEnabled(true);

                                pack();

                            }
                        });

                    }
                });

            }
        }
    }//GEN-LAST:event_use_mega_account_down_comboboxItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton change_dir_button;
    private javax.swing.JButton dance_button;
    private javax.swing.JButton dlc_button;
    private javax.swing.JLabel down_dir_to_label;
    private javax.swing.JLabel download_dir_label;
    private javax.swing.JLabel links_label;
    private javax.swing.JScrollPane links_scrollpane;
    private javax.swing.JTextArea links_textarea;
    private javax.swing.JComboBox<String> use_mega_account_down_combobox;
    private javax.swing.JLabel use_mega_account_down_label;
    // End of variables declaration//GEN-END:variables

    @Override
    public void notifyClipboardChange() {

        swingInvoke(new Runnable() {
            @Override
            public void run() {

                String current_text = links_textarea.getText();

                links_textarea.append((current_text.length() > 0 ? "\n\n" : "") + extractMegaLinksFromString(extractStringFromClipboardContents(_clipboardspy.getContents())));
            }
        });
    }
    private static final Logger LOG = Logger.getLogger(LinkGrabberDialog.class.getName());
}
