
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class About extends JFrame implements ActionListener {

    About(){

        setBounds(400, 100, 600, 500);
        setLayout(null);
        ImageIcon il = new ImageIcon(ClassLoader.getSystemResource("icons/windows.png"));
        Image i2 = il.getImage().getScaledInstance(300, 60, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel headerIcon = new JLabel(i3);
        headerIcon.setBounds(70, 40, 400, 60);
        add(headerIcon);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/icon.png"));
        Image i5 = i4.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel icon = new JLabel(i6);
        icon.setBounds(70, 180, 70, 70);
        add(icon);

        JLabel text = new JLabel("<html>Code for fun<br>Version 0.0.1</html>");
        text.setBounds(150, 100, 500, 200);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(text);

        JButton b1 = new JButton("OK"); 
        b1.setBounds(150, 350, 120, 25);
        b1.addActionListener(this);
        add(b1);

        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
    }
    
    public static void main(String[] args) {
       new About();
    }
}
