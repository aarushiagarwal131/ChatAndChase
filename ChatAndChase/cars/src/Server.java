package cars;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;
public class Server1 implements ActionListener {
  private JButton play;
  JTextField text;
  JPanel p;
  static JFrame f = new JFrame();
  static Box vertical = Box.createVerticalBox();
  static DataOutputStream dout;
  Server1() {
    f.setLayout(null);
    JPanel p1 = new JPanel();
    p1.setBackground(new Color(7, 94, 84));
    p1.setBounds(0, 0, 450, 70);
    p1.setLayout(null);
    f.add(p1);

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("cars/3.png"));
    Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel back = new JLabel(i3);
    back.setBounds(5, 20, 25, 25);
    p1.add(back);
    back.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent ae) {
        System.exit(0);
      }
    });
    ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("cars/aaru.jpg"));
    Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
    ImageIcon i6 = new ImageIcon(i5);
    JLabel profile = new JLabel(i6);
    profile.setBounds(40, 10, 60, 60);
    p1.add(profile);

    ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("cars/video.png"));
    Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
    ImageIcon i9 = new ImageIcon(i8);
    JLabel video = new JLabel(i9);
    video.setBounds(300, 20, 30, 30);
    p1.add(video);

    ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("cars/phone.png"));
    Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
    ImageIcon i12 = new ImageIcon(i11);
    JLabel phone = new JLabel(i12);
    phone.setBounds(360, 20, 35, 30);
    p1.add(phone);

    ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("cars/3icon.png"));
    Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
    ImageIcon i15 = new ImageIcon(i14);
    JLabel threeDot = new JLabel(i15);
    threeDot.setBounds(420, 20, 10, 25);
    p1.add(threeDot);

    JLabel name = new JLabel("Aarushi");
    name.setBounds(110, 15, 100, 18);
    name.setForeground(Color.WHITE);
    name.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
    p1.add(name);

    JLabel status = new JLabel("Online");
    status.setBounds(110, 35, 100, 18);
    status.setForeground(Color.WHITE);
    status.setFont(new Font("SAN_SERIF", Font.BOLD, 13));
    p1.add(status);

    p = new JPanel();
    p.setBounds(5, 75, 440, 570);
    f.add(p);

    text = new JTextField();
    text.setBounds(5, 550, 310, 40);
    text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
    f.add(text);

    JButton send = new JButton("Send");
    send.setBounds(320, 550, 123, 40);
    send.setBackground(new Color(7, 94, 84));
    send.setForeground(Color.white);

    send.setFont(new Font("SEN_SERIF", Font.PLAIN, 16));
    f.add(send);
    send.addActionListener(this);

    play = new JButton("PLAY");
    play.setBounds(320, 500, 123, 40);
    play.setBackground(new Color(0, 45, 193));
    play.setForeground(Color.white);
    play.setFont(new Font("SEN_SERIF", Font.PLAIN, 16));
    f.add(play);
    play.addActionListener(this);

    f.setLocation(100, 90);
    f.setUndecorated(true);
    f.setSize(450, 600);
    f.getContentPane().setBackground(Color.WHITE);
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == play) {

      Test.caller();
    }
    try {
      String out = text.getText();
      JLabel output = new JLabel(out);
      JPanel p2 = formatLabel(out);
      p.setLayout(new BorderLayout());
      JPanel right = new JPanel(new BorderLayout());
      right.add(p2, BorderLayout.LINE_END);
      vertical.add(right);
      vertical.add(Box.createVerticalStrut(15));
      p.add(vertical, BorderLayout.PAGE_START);
      dout.writeUTF(out);
      text.setText("");
      f.repaint();
      f.invalidate();
      f.validate();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  public static JPanel formatLabel(String out) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
    output.setFont(new Font("Tahoma", Font.PLAIN, 16));
    output.setBackground(new Color(37, 211, 102));
    output.setOpaque(true);
    output.setBorder(new EmptyBorder(15, 15, 15, 50));
    panel.add(output);
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    JLabel time = new JLabel();
    time.setText(sdf.format(cal.getTime()));
    panel.add(time);
    return panel;
  }
  public static void main(String[] args) {
    new Server1();
    try {
      ServerSocket skt = new ServerSocket(6001);
      while (true) {
        Socket s = skt.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());
        while (true) {
          String msg = din.readUTF();
          JPanel panel = formatLabel(msg);
          JPanel left = new JPanel(new BorderLayout());
          left.add(panel, BorderLayout.LINE_START);
          vertical.add(left);
          f.validate();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
