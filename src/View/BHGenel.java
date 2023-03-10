  package View;
  
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import DataBase.Bashekim;
import DataBase.Doktor;
import DataBase.Hasta;
import DataBase.Poliklinik;
import Helper.DBConnection;
import Helper.Helper;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;


public class BHGenel extends JFrame {
	
	JInternalFrame iFDoktorBilgi = new JInternalFrame("DoktorBİlgisi");
	JInternalFrame iFDoktorEkle = new JInternalFrame("Doktor Ekle Çıkar");
	Doktor doktor = new Doktor();
	Poliklinik polikilinik =new Poliklinik();
			
	private DBConnection conn = new DBConnection();
	private JPanel contentPane;
	 static Bashekim bshekim = new Bashekim();
	 private JTextField tfAd;
	 private JTextField tfSoyad;
	 private JTextField tfTcno;
	 private JTextField tfDogumTarih;
	 private JTextField tfPoliklinik;
	 private JPasswordField tfSifre;
	 private JTextField tfPoliklinikAd;
	 private JTextField tfPoliklinikId;
	 private JTextField tSoyad;
	 private JTextField tTCNO;
	 private JTextField tSifre;
	 private JTextField tPoliklinik;
	 private JTextField tDogumTarihi;
	 private JTextField tAd;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BHGenel frame = new BHGenel(bshekim);
					frame.setTitle("Baş Hekim Sayfası");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public BHGenel(Bashekim bshekim) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 750);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(204, 255, 255));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton butonDoktorBilgisiBHekim = new JButton("Doktor bilgisi");
		butonDoktorBilgisiBHekim.setFont(new Font("Arial", Font.BOLD, 12));
		butonDoktorBilgisiBHekim.setBounds(21, 95, 174, 104);
		butonDoktorBilgisiBHekim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iFDoktorBilgi.setVisible(true);
				iFDoktorEkle.setVisible(false);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(butonDoktorBilgisiBHekim);
		
		JButton butonDoktorEkleBHekim = new JButton("Doktor ve Poliklinik\r\nEkle");
		butonDoktorEkleBHekim.setFont(new Font("Arial", Font.BOLD, 12));
		butonDoktorEkleBHekim.setMinimumSize(new Dimension(30, 15));
		butonDoktorEkleBHekim.setMaximumSize(new Dimension(30, 15));
		butonDoktorEkleBHekim.setIconTextGap(0);
		butonDoktorEkleBHekim.setBounds(21, 238, 170, 104);
		butonDoktorEkleBHekim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iFDoktorEkle.setVisible(true);
				iFDoktorBilgi.setVisible(false);
				
			}
		});
		contentPane.add(butonDoktorEkleBHekim);
		iFDoktorBilgi.setBounds(221, 67, 940, 587);
		iFDoktorBilgi.addMouseListener(new MouseAdapter() {
			
		});
		iFDoktorBilgi.setBackground(new Color(255, 255, 255));
		iFDoktorBilgi.setEnabled(false);
		iFDoktorBilgi.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		contentPane.add(iFDoktorBilgi);
		iFDoktorBilgi.getContentPane().setLayout(null);
		
		
		JList<String> list = new JList<>(doktor.doktorList());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(new TitledBorder(null, "Doktorlar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		list.setBounds(10, 11, 228, 538);
		iFDoktorBilgi.getContentPane().add(list);
	
		
		JLabel lblNewLabel = new JLabel("Soyad : ");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(281, 134, 224, 27);
		iFDoktorBilgi.getContentPane().add(lblNewLabel);
		
		JLabel lblTcNo = new JLabel("T.C. No : ");
		lblTcNo.setFont(new Font("Arial", Font.BOLD, 24));
		lblTcNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTcNo.setBounds(281, 191, 224, 27);
		iFDoktorBilgi.getContentPane().add(lblTcNo);
		
		JLabel lblPoliklinik = new JLabel("Poliklinik : ");
		lblPoliklinik.setFont(new Font("Arial", Font.BOLD, 24));
		lblPoliklinik.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPoliklinik.setBounds(281, 317, 224, 27);
		iFDoktorBilgi.getContentPane().add(lblPoliklinik);
		
		JLabel lblifre = new JLabel("Şifre : ");
		lblifre.setFont(new Font("Arial", Font.BOLD, 24));
		lblifre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblifre.setBounds(281, 253, 224, 27);
		iFDoktorBilgi.getContentPane().add(lblifre);
		
		JLabel lblDogumTarihi = new JLabel("Dogum Tarihi : ");
		lblDogumTarihi.setFont(new Font("Arial", Font.BOLD, 24));
		lblDogumTarihi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDogumTarihi.setBounds(281, 379, 224, 27);
		iFDoktorBilgi.getContentPane().add(lblDogumTarihi);
		
		JButton btnbilgiler = new JButton("Bilgileri Göster");
		btnbilgiler.setBorder(UIManager.getBorder("Button.border"));
		btnbilgiler.setFont(new Font("Segoe Print", Font.BOLD, 16));
		btnbilgiler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedValue().equals(null))
				{
					Helper.showMsg("Öncelikle Doktor Seçmelisiniz !!");
				}else {
				
				try {
					Connection con = conn.connDb();
					Statement st = con.createStatement();
					String query=("SELECT * FROM user "+" WHERE ad='"+list.getSelectedValue()+"' ");
					ResultSet rs;
					rs = st.executeQuery(query);
					rs.next();
					
					tAd.setText(rs.getString("ad").toUpperCase());
					tSoyad.setText(rs.getString("soyad").toUpperCase());
					tTCNO.setText(rs.getString("tcno"));
					tDogumTarihi.setText(rs.getString("dogumTarih"));
					tPoliklinik.setText(rs.getString("Poliklinik"));
					tSifre.setText(rs.getString("sifre"));
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
		
			}
		});
		btnbilgiler.setBounds(541, 444, 176, 54);
		iFDoktorBilgi.getContentPane().add(btnbilgiler);
		
		tSoyad = new JTextField();
		tSoyad.setBounds(515, 134, 378, 27);
		iFDoktorBilgi.getContentPane().add(tSoyad);
		tSoyad.setColumns(10);
		
		tTCNO = new JTextField();
		tTCNO.setColumns(10);
		tTCNO.setBounds(515, 191, 378, 27);
		iFDoktorBilgi.getContentPane().add(tTCNO);
		
		tSifre = new JTextField();
		tSifre.setColumns(10);
		tSifre.setBounds(515, 253, 378, 27);
		iFDoktorBilgi.getContentPane().add(tSifre);
		
		tPoliklinik = new JTextField();
		tPoliklinik.setColumns(10);
		tPoliklinik.setBounds(515, 317, 378, 27);
		iFDoktorBilgi.getContentPane().add(tPoliklinik);
		
		tDogumTarihi = new JTextField();
		tDogumTarihi.setColumns(10);
		tDogumTarihi.setBounds(515, 379, 378, 27);
		iFDoktorBilgi.getContentPane().add(tDogumTarihi);
		
		JLabel lblAd = new JLabel(" Ad : ");
		lblAd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAd.setFont(new Font("Arial", Font.BOLD, 24));
		lblAd.setBounds(277, 87, 228, 27);
		iFDoktorBilgi.getContentPane().add(lblAd);
		
		tAd = new JTextField();
		tAd.setColumns(10);
		tAd.setBounds(515, 87, 378, 27);
		iFDoktorBilgi.getContentPane().add(tAd);
		
		JButton btnBilgileriGncelle = new JButton("Bilgileri Güncelle");
		btnBilgileriGncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tAd.getText().length() == 0 || tSoyad.getText().length() ==0 || tDogumTarihi.getText().length()==0 || 
							tSifre.getText().length() == 0 || tTCNO.getText().length()==0||tPoliklinik.getText().length()==0) {
						Helper.showMsg("fill");	
					}
					
					Connection con = conn.connDb();
					Statement st = con.createStatement();
					String query=("SELECT * FROM user "+" WHERE ad='"+list.getSelectedValue()+"' ");
					ResultSet rs;
					rs = st.executeQuery(query);
					rs.next();
					doktor.DoktorGuncelleme(rs.getString("tcno"),tTCNO.getText(), tAd.getText(),tSoyad.getText(), tSifre.getText(), tDogumTarihi.getText(), tPoliklinik.getText());
					
					String query2=("SELECT * FROM user "+" WHERE ad='"+tAd.getText()+"' ");
				
					rs = st.executeQuery(query2);
					rs.next();
					Helper.showMsg("Bilgiker Güncellendi.");
					tAd.setText(null);
					tSoyad.setText(null);
					tTCNO.setText(null);
					tDogumTarihi.setText(null);
					tPoliklinik.setText(null);
					tSifre.setText(null);
					
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnBilgileriGncelle.setFont(new Font("Segoe Print", Font.BOLD, 16));
		btnBilgileriGncelle.setBorder(UIManager.getBorder("Button.border"));
		btnBilgileriGncelle.setBounds(752, 444, 176, 54);
		iFDoktorBilgi.getContentPane().add(btnBilgileriGncelle);
		iFDoktorEkle.setBounds(220, 65, 941, 589);
		iFDoktorEkle.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		iFDoktorEkle.setBackground(new Color(255, 255, 255));
		contentPane.add(iFDoktorEkle);
		iFDoktorEkle.getContentPane().setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(597, 90, 321, 342);
		iFDoktorEkle.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ad : ");
		lblNewLabel_1.setBounds(10, 0, 145, 36);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblNewLabel_1_1 = new JLabel("Soyad : ");
		lblNewLabel_1_1.setBounds(20, 47, 145, 36);
		panel.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblNewLabel_1_2 = new JLabel("T.C. No : ");
		lblNewLabel_1_2.setBounds(10, 94, 145, 36);
		panel.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblNewLabel_1_3 = new JLabel("Sifre : ");
		lblNewLabel_1_3.setBounds(10, 136, 145, 36);
		panel.add(lblNewLabel_1_3);
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblNewLabel_1_4 = new JLabel("DogumTarihi :");
		lblNewLabel_1_4.setBounds(10, 178, 145, 36);
		panel.add(lblNewLabel_1_4);
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		tfDogumTarih = new JTextField();
		tfDogumTarih.setBounds(165, 189, 119, 20);
		panel.add(tfDogumTarih);
		tfDogumTarih.setColumns(10);
		
		tfTcno = new JTextField();
		tfTcno.setBounds(165, 105, 119, 20);
		panel.add(tfTcno);
		tfTcno.setColumns(10);
		
		tfSoyad = new JTextField();
		tfSoyad.setBounds(165, 58, 119, 20);
		panel.add(tfSoyad);
		tfSoyad.setColumns(10);
		
		tfAd = new JTextField();
		tfAd.setBounds(165, 11, 119, 20);
		panel.add(tfAd);
		tfAd.setColumns(10);
		
		JButton btnEkle = new JButton("Doktor Ekle");
		btnEkle.setBounds(174, 274, 110, 23);
		panel.add(btnEkle);
		
		tfPoliklinik = new JTextField();
		tfPoliklinik.setColumns(10);
		tfPoliklinik.setBounds(165, 236, 119, 20);
		panel.add(tfPoliklinik);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Polklinik Id :");
		lblNewLabel_1_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_4_1.setBounds(10, 225, 145, 36);
		panel.add(lblNewLabel_1_4_1);
		
		tfSifre = new JPasswordField();
		tfSifre.setBounds(165, 147, 119, 20);
		panel.add(tfSifre);
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int c=1;
				if(tfAd.getText().length() == 0 || tfSoyad.getText().length() ==0 || tfDogumTarih.getText().length()==0 || 
						tfSifre.getText().length() == 0 || tfTcno.getText().length()==0||tfPoliklinik.getText().length()==0) {
					Helper.showMsg("fill");	
				}
				else {
					Connection con = conn.connDb();
					try {
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if (tfTcno.getText().equals(rs.getString("tcno"))) {
								Helper.showMsg("Aynı Kimlikle Başka Bir Kulanıcı var.");
								c = 0;
							} else if (tfTcno.getText().length() != 11) {
								Helper.showMsg("Lütfen 11 hane giriniz.");
								c = 0; 
								break;
							}

						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if(c==1) {

						try {
							Hasta hasta = new Hasta();
							doktor.KayitOl(tfTcno.getText(), tfAd.getText(), tfSoyad.getText(),
									tfDogumTarih.getText(), tfSifre.getText(),Integer.parseInt(tfPoliklinik.getText()));
							Helper.showMsg("success");
							
						
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				
					
					tfAd.setText(null);
					tfDogumTarih.setText(null);
					tfPoliklinik.setText(null);
					tfSifre.setText(null);
					tfSoyad.setText(null);
					tfTcno.setText(null);
				
					
				}
		
			}
			

		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(252, 90, 321, 342);
		iFDoktorEkle.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1_5 = new JLabel("Poliklinik Adi");
		lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_5.setBounds(10, 95, 145, 36);
		panel_1.add(lblNewLabel_1_5);
		
		tfPoliklinikAd = new JTextField();
		tfPoliklinikAd.setColumns(10);
		tfPoliklinikAd.setBounds(165, 106, 119, 20);
		panel_1.add(tfPoliklinikAd);
		
		JButton btnEkle_1 = new JButton("Poliklinik Ekle");
		btnEkle_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfPoliklinikAd.getText().length() == 0 || tfPoliklinikId.getText().length() == 0) {
					Helper.showMsg("fill");
				}
				else {
					int q=1;
					try {
						Connection con = conn.connDb();
						Statement st;
						st = con.createStatement();
						String query=("SELECT * FROM poliklinik "+" WHERE id");
						ResultSet rs = st.executeQuery(query);
						while(rs.next()) {
							System.out.println(rs.getString("id"));
						if(tfPoliklinikId.getText().equals(rs.getString("id"))) {
						Helper.showMsg("Aynı id'ye Sahip Polklinik Zaten var!!");
						tfPoliklinikAd.setText(null);
						tfPoliklinikId.setText(null);
						q=0;
						break;
							}
						}
						if(q == 1) {
								polikilinik.polkilinikEkle(tfPoliklinikAd.getText(),Integer.parseInt(tfPoliklinikId.getText()));
								Helper.showMsg("success");
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tfPoliklinikAd.setText(null);
					tfPoliklinikId.setText(null);
					

				
				}
			}
		});
		btnEkle_1.setBounds(201, 270, 110, 23);
		panel_1.add(btnEkle_1);
		
		tfPoliklinikId = new JTextField();
		tfPoliklinikId.setColumns(10); 
		tfPoliklinikId.setBounds(165, 156, 119, 20);
		panel_1.add(tfPoliklinikId);
		
		JLabel lblNewLabel_1_4_1_1 = new JLabel("Polklinik Id :");
		lblNewLabel_1_4_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_4_1_1.setBounds(10, 145, 145, 36);
		panel_1.add(lblNewLabel_1_4_1_1);
		
		JLabel arkaplan = new JLabel(new ImageIcon(this.getClass().getResource("arka2.png")));
		arkaplan.setFont(new Font("Tahoma", Font.BOLD, 16));
		arkaplan.setText("Hoşgeldiniz Başhekim :");
		arkaplan.setBackground(new Color(255, 255, 255));
		arkaplan.setBounds(0, 0, 1184, 711);
		contentPane.add(arkaplan);
		
		
		
		JList<String> listid = new JList(polikilinik.poliklinikListId());
		listid.setEnabled(false);
		listid.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "iD", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		listid.setBounds(10, 47, 47, 505);
		iFDoktorEkle.getContentPane().add(listid);
		
		JList<String> listPoliklinik = new JList(polikilinik.poliklinikListAD());
		listPoliklinik.setEnabled(false);
		listPoliklinik.setBorder(new TitledBorder(null, "Poliklinikler", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listPoliklinik.setBounds(67, 47, 164, 507);
		iFDoktorEkle.getContentPane().add(listPoliklinik);
		
		JLabel lblNewLabel_2 = new JLabel("Hoşgeldiniz BaşHekim ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(34, 21, 354, 35);
		contentPane.add(lblNewLabel_2);
		iFDoktorEkle.setVisible(false);
		iFDoktorBilgi.setVisible(true);
	}
}
