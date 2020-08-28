import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class Gui {
	LinkedList<ChampsC> linky = new LinkedList<ChampsC>();
	ChampsC champ = new ChampsC("", null, null);
	int wrong = 0;

	private JFrame frmLeaguesoundgame;
	private JTextField textField;
	private JButton goBttn;
	int score = 0;
	int r;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Gui window = new Gui();
					window.frmLeaguesoundgame.setVisible(true);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	// Create the application.

	public Gui() throws IOException 
	{

		initialize();
	}

	// Initialize the contents of the frame.

	public int random() 
	{
		Random rand = new Random();
		int r = rand.nextInt(linky.size());
		return r;
	}

	public int getRandom() 
	{
		return r;
	}

	private void initialize() throws IOException 
	{

		// JFrame Stuff
		frmLeaguesoundgame = new JFrame();
		frmLeaguesoundgame.setTitle("LeagueSoundGame ");
		frmLeaguesoundgame.setBounds(100, 100, 300, 300);
		frmLeaguesoundgame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLeaguesoundgame.getContentPane().setLayout(null);
		JButton soundBttn = new JButton("Audio");
		
		// CSV with all champ names
		String CSV = "Aatrox,Ahri,Akali,Alistar,Amumu,Anivia,Annie,Aphelios,Ashe,Aurelion Sol,Azir,Bard,Blitzcrank,Brand,Braum,Caitlyn,Camille,Cassiopeia,Cho'Gath,"
				+ "Corki,Darius,Diana,Dr. Mundo,Draven,Ekko,Elise,Evelynn,Ezreal,Fiddlesticks,Fiora,Fizz,Galio,Gangplank,Garen,Gnar,Gragas,Graves,Hecarim,"
				+ "Heimerdinger,Illaoi,Irelia,Ivern,Janna,Jarvan IV,Jax,Jayce,Jhin,Jinx,Kai'Sa,Kalista,Karma,Karthus,Kassadin,Katarina,Kayle,Kayn,Kennen,Kha'Zix,"
				+ "Kindred,Kled,Kog'Maw,LeBlanc,Lee Sin,Leona,Lissandra,Lucian,Lulu,Lux,Malphite,Malzahar,Maokai,Master Yi,Miss Fortune,Mordekaiser,Morgana,Nami,"
				+ "Nasus,Nautilus,Neeko,Nidalee,Nocturne,Nunu,Olaf,Orianna,Ornn,Pantheon,Poppy,Pyke,Qiyana,Quinn,Rakan,Rammus,Rek'Sai,Renekton,Rengar,Riven,Rumble,"
				+ "Ryze,Sejuani,Senna,Sett,Shaco,Shen,Shyvana,Singed,Sion,Sivir,Skarner,Sona,Soraka,Swain,Sylas,Syndra,Tahm Kench,Taliyah,Talon,Taric,Teemo,Thresh,"
				+ "Tristana,Trundle,Tryndamere,Twisted Fate,Twitch,Udyr,Urgot,Varus,Vayne,Veigar,Vel'Koz,Vi,Viktor,Vladimir,Volibear,Warwick,Wukong,Xayah,Xerath,"
				+ "Xin Zhao,Yasuo,Yorick,Yuumi,Zac,Zed,Ziggs,Zilean,Zoe,Zyra,Kindred,Illaoi,Jhin,Aurelion Sol, Talliyah,Kled,Ivern,Camille,Rakan,Xayah,Kayn,Ornn,"
				+ "Zoe,Kai'Sa,Pyke,Neeko,Sylas,Yuumi,Qiyana,Senna,Aphelios,Sett";
		// Putting each name into an array
		String[] values = CSV.split(",");



		for (int i = 0; i < values.length; i++)
		{
			// Auto making all the objects


			linky.add(new ChampsC(values[i], new File("C:\\Users\\Spider\\Desktop\\LeageSoundGame\\src\\Resources\\Sounds\\" + values[i] + ".wav"),
					new File("C:\\Users\\Spider\\Desktop\\LeageSoundGame\\src\\Resources\\Images\\" + values[i] + ".jpg")));
		}

		soundBttn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int r2 = getRandom();
				// Play audio
				playAudio(linky.get(r2).sound);
			}

			// method to play the wav files
			public void playAudio(File Sound)
			{
				try
				{
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(Sound));
					clip.start();
				}
				catch (Exception e)
				{

				}
			}
		});

		// Gui stuff 
		soundBttn.setBounds(10, 206, 68, 44);
		frmLeaguesoundgame.getContentPane().add(soundBttn);

		textField = new JTextField();
		textField.setBounds(95, 218, 86, 20);
		frmLeaguesoundgame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel scoreLbl = new JLabel();
		scoreLbl.setText("Score : ");
		scoreLbl.setBounds(122, 11, 119, 14);
		frmLeaguesoundgame.getContentPane().add(scoreLbl);

		panel = new JPanel();
		panel.setBounds(34, 35, 207, 160);
		frmLeaguesoundgame.getContentPane().add(panel);

		JLabel jl = new JLabel();
		panel.add(jl);

		goBttn = new JButton("Go");

		goBttn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int r1 = getRandom();
				String bigL = linky.get(r1).name;
				System.out.println(bigL);

				// matching the text with the audio
				if (textField.getText().equalsIgnoreCase(bigL)) 
				{
					score++;
					scoreLbl.setText("Score: " + Integer.toString(score));
					r = random();
					try 
					{
						// displaying the image colerating with the audio
						BufferedImage img = ImageIO.read(linky.get(r1).images);
						Image modImage = img.getScaledInstance(200, 160, java.awt.Image.SCALE_SMOOTH);
						jl.setIcon(new ImageIcon(modImage));
					} 
					catch (IOException e1) 
					{
						e1.printStackTrace();
					}
					textField.setText("");
					System.out.println(getRandom());
				}

				else if (wrong == 2) 
				{
					main(values);
					score = 0;
				}

				else 
				{
					wrong++;
					textField.setText("Wrong xD:");
					score = 0;
					System.out.println(wrong);
				}
			}
		});
		goBttn.setBounds(202, 217, 56, 23);
		frmLeaguesoundgame.getContentPane().add(goBttn);
	}
}
