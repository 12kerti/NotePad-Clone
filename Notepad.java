
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
public class Notepad extends JFrame implements ActionListener{

    JTextArea area;
    String text;
    Notepad(){
        setTitle("Notepad");
        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("icons/icon.png"));
        Image icon = notepadIcon.getImage();
        setIconImage(icon);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.GRAY);

        JMenu file = new JMenu("File");
        file.setFont(new Font("ARIAL", Font.PLAIN, 14));

        JMenuItem newDoc = new JMenuItem("New");
        newDoc.addActionListener(this);
        newDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        JMenuItem print = new JMenuItem("Print");
        print.addActionListener(this);
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.CTRL_MASK));


        file.add(newDoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

        JMenu edit = new JMenu("Edit");
        edit.setFont(new Font("ARIAL", Font.PLAIN, 14));

        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));

        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.addActionListener(this);
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectAll);

        
        menuBar.add(file);
        menuBar.add(edit);

        JMenu helpmenu = new JMenu("Help");
        helpmenu.setFont(new Font("ARIAL", Font.PLAIN, 14));

        JMenuItem help = new JMenuItem("About");
        help.addActionListener(this);
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));

        helpmenu.add(help);
        menuBar.add(helpmenu);
        
        setJMenuBar(menuBar);

        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        add(area);

        JScrollPane pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane);


        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if (ae.getActionCommand().equals("New")){
            area.setText("");
        }
        else if (ae.getActionCommand().equals("Open")){
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
            chooser.addChoosableFileFilter(restrict);

            int action = chooser.showOpenDialog(this);

            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }
            File file = chooser.getSelectedFile();
            try{
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null);
            } catch (Exception e){
                e.printStackTrace();
            }
        } else if (ae.getActionCommand().equals("Save")){
        JFileChooser saveAs = new JFileChooser();
        saveAs.setApproveButtonText("Save");
        int action = saveAs.showOpenDialog(this);

            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }

            File fileName = new File(saveAs.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try{
                outFile = new BufferedWriter(new FileWriter(fileName));
                area.write(outFile);
            }
            catch (Exception e){
                e.printStackTrace();
            }
    }
        else if (ae.getActionCommand().equals("Print")){
        try{
            area.print();
        }
        catch (Exception e){
            e.printStackTrace();
        }   
    }
    else if (ae.getActionCommand().equals("Exit")){
        System.exit(0);
    }
    else if (ae.getActionCommand().equals("Copy")){
        text = area.getSelectedText();
    }
    else if (ae.getActionCommand().equals("Paste")){
        area.insert(text, area.getCaretPosition());
    }
    else if (ae.getActionCommand().equals("Cut")){
        text = area.getSelectedText();
        area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());

    } else if (ae.getActionCommand().equals("Select All")){
        area.selectAll();
    }
    else if (ae.getActionCommand().equals("About")){
        new About().setVisible(true);
    }
}


    public static void main(String[] args) {
        new Notepad(); //class object (calls default constructer)

    }
}
