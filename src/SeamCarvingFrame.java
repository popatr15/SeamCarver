import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SeamCarvingFrame extends JFrame implements ActionListener
{
	private SeamCarvingPanel mainPanel;
	private JButton findSeamButton, shrinkButton, loadButton, calculateEnergyButton;
	private JButton reduceToStartButton, shrinkCycleButton;
	private File theFile;
	private JSpinner nSpinner;
	
	SeamCarvingFrame()
	{
		super("Seam Carving.");
		setSize(1000,800);
		setResizable(true);
		getContentPane().setLayout(new BorderLayout());
		mainPanel = new SeamCarvingPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		getContentPane().add(buildNorthPanel(),BorderLayout.NORTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFile = new File(System.getProperty("user.home")+"/Documents/");
		setVisible(true);
	}
	
	private JPanel buildNorthPanel()
	{
		JPanel northPanel = new JPanel();
		loadButton = new JButton("Load Image");
		loadButton.addActionListener(this);
		northPanel.add(loadButton);
		
		JPanel cyclePanel = new JPanel();
		cyclePanel.setBorder(new TitledBorder("Cycle"));
		
		calculateEnergyButton = new JButton("Calculate Energy");
		calculateEnergyButton.addActionListener(this);
		cyclePanel.add(calculateEnergyButton);
		
		
		findSeamButton = new JButton("Find Seam");
		findSeamButton.addActionListener(this);
		cyclePanel.add(findSeamButton);
		
		shrinkButton = new JButton("Shrink");
		shrinkButton.addActionListener(this);
		cyclePanel.add(shrinkButton);
		
		reduceToStartButton = new JButton("Start <-- Reduce");
		reduceToStartButton.addActionListener(this);
		cyclePanel.add(reduceToStartButton);
		northPanel.add(cyclePanel);
		
		shrinkCycleButton = new JButton("Do N Cycles");
		shrinkCycleButton.addActionListener(this);
		northPanel.add(shrinkCycleButton);
		
		nSpinner = new JSpinner(new SpinnerNumberModel(1,1,100,1));
		northPanel.add(nSpinner);
		
		return northPanel;
	}
	public void handleLoadEvent()
	{
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(theFile);
		fc.setFileFilter(new FileNameExtensionFilter("Image Files","jpg","JPG","JPEG","jpeg","png","PNG","gif","GIF"));
		int result = fc.showOpenDialog(this);
		if (result != JFileChooser.CANCEL_OPTION)
		{
			theFile = fc.getSelectedFile();
			mainPanel.loadImage(theFile);
		}	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent actEvt)
	{
		// TODO Auto-generated method stub
		if (actEvt.getSource() == loadButton)
			handleLoadEvent();
		if (actEvt.getSource() == calculateEnergyButton)
			mainPanel.doCalculateEnergy();
		if (actEvt.getSource() == findSeamButton)
			mainPanel.doFindSeam();
		if (actEvt.getSource() == shrinkButton)
			mainPanel.doShrink();
		if (actEvt.getSource() == reduceToStartButton)
			mainPanel.doReducedToStart();
		if (actEvt.getSource() == shrinkCycleButton)
			mainPanel.doShrinkCycle(((SpinnerNumberModel)nSpinner.getModel()).getNumber().intValue());
	}
}
