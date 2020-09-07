package com.firebirdcss.tool.developer_tools;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import com.firebirdcss.tool.developer_tools.utils.Function;

public class MainWindow {
    private JTextArea txtStringInput;
    private JTextArea txtStringOutput;
    private JComboBox<String> cbxFunctions;
    
    private JFrame frame;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow window = new MainWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the application.
     */
    public MainWindow() {
        initialize();
    }
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 384);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SpringLayout springLayout = new SpringLayout();
        frame.getContentPane().setLayout(springLayout);
        
        /* ******************* *
         * JLabel: lblNewLabel *
         * ******************* */
        JLabel lblNewLabel = new JLabel("Function to Perform:");
        springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, frame.getContentPane());
        frame.getContentPane().add(lblNewLabel);
        
        /* *********************** *
         * JComboBox: cbxFunctions *
         * *********************** */
        cbxFunctions = new JComboBox<>();
        cbxFunctions.setModel(new DefaultComboBoxModel<String>(
                new String[] {
                    "Multi-Line to Single Line", 
                    "Make JSON Pretty", 
                    "Unescape Java String", 
                    "Remove Fields from JSON Object", 
                    "Limit JSON Array"
                }
        ));
        springLayout.putConstraint(SpringLayout.NORTH, cbxFunctions, 6, SpringLayout.SOUTH, lblNewLabel);
        springLayout.putConstraint(SpringLayout.WEST, cbxFunctions, 0, SpringLayout.WEST, lblNewLabel);
        frame.getContentPane().add(cbxFunctions);
        
        JButton btnActivate = new JButton("Activate");
        btnActivate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doActivate();
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, btnActivate, 0, SpringLayout.NORTH, lblNewLabel);
        springLayout.putConstraint(SpringLayout.EAST, btnActivate, -10, SpringLayout.EAST, frame.getContentPane());
        frame.getContentPane().add(btnActivate);
        
        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doReset();
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, btnReset, 0, SpringLayout.SOUTH, btnActivate);
        springLayout.putConstraint(SpringLayout.EAST, btnReset, -10, SpringLayout.EAST, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, btnReset, 0, SpringLayout.WEST, btnActivate);
        frame.getContentPane().add(btnReset);
        
        JButton btnPasteIn = new JButton("Paste Input");
        btnPasteIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doPasteIn();
            }
        });
        springLayout.putConstraint(SpringLayout.WEST, btnPasteIn, 0, SpringLayout.WEST, lblNewLabel);
        frame.getContentPane().add(btnPasteIn);
        
        /* ******************* *
         * JButton: btnCopyOut * 
         * ******************* */
        JButton btnCopyOut = new JButton("Copy Output");
        btnCopyOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doCopyOut();
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, btnPasteIn, 0, SpringLayout.NORTH, btnCopyOut);
        springLayout.putConstraint(SpringLayout.NORTH, btnCopyOut, 6, SpringLayout.SOUTH, btnReset);
        springLayout.putConstraint(SpringLayout.EAST, btnCopyOut, 0, SpringLayout.EAST, btnActivate);
        frame.getContentPane().add(btnCopyOut);
        
        /* ********************************************** *
         * JSplitPane: [LEFT] : JTextArea: txtStringInput *
         * ********************************************** */
        txtStringInput = new JTextArea();
        
        /* ************************************************ *
         * JSplitPane: [RIGHT] : JTextArea: txtStringOutput *
         * ************************************************ */
        txtStringOutput = new JTextArea();
        
        JScrollPane jScrollInput = new JScrollPane(txtStringInput);
        JScrollPane jScrollOutput = new JScrollPane(txtStringOutput);
        
        /* ****************** *
         * JSplitPane: jSplit *
         * ****************** */
        JSplitPane jSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollInput, jScrollOutput);
        springLayout.putConstraint(SpringLayout.NORTH, jSplit, 0, SpringLayout.SOUTH, btnCopyOut);
        springLayout.putConstraint(SpringLayout.SOUTH, jSplit, -6, SpringLayout.SOUTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, jSplit, 6, SpringLayout.WEST, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, jSplit, -6, SpringLayout.EAST, frame.getContentPane());
        jSplit.setResizeWeight(.5D);
        frame.getContentPane().add(jSplit);
        
    }
    
    /**
     * ACTION: btnPasteIn
     * 
     */
    private void doPasteIn() {
        String result = "";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        
        boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        
        if (hasTransferableText) {
            try {
                result = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException ex){
                System.out.println(ex);
                ex.printStackTrace();
            }
        }
        txtStringInput.setText(result);
    }
    
    /**
     * ACTION: btnCopyOut
     */
    private void doCopyOut() {
        StringSelection stringSelection = new StringSelection(txtStringOutput.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
    
    private void doActivate() {
        switch ((String) cbxFunctions.getSelectedItem()) {
            case "Multi-Line to Single Line":
                txtStringOutput.setText(Function.multiLineToSingleLine(txtStringInput.getText()));
                break;
            case "Unescape Java String":
                txtStringOutput.setText(Function.unescapeJavaString(txtStringInput.getText()));
                break;
            case "Make JSON Pretty":
                txtStringOutput.setText(Function.makeJsonPretty(txtStringInput.getText()));
                break;
            default:
                JOptionPane.showMessageDialog(this.frame, "Oops, Somehow your selected function was invalid; Try again!");
                break;
        }
    }
    
    private void doReset() {
        StringSelection stringSelection = new StringSelection(txtStringInput.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        
        txtStringOutput.setText("");
        txtStringInput.setText("");
    }
}
