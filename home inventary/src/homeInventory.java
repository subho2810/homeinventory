import java.io.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import com.toedter.calendar.*;
import java.beans.*;
import java.awt.geom.*;
import java.util.*;
import java.text.*;
import javax.swing.filechooser.*;
import java.awt.print.*;

public class homeInventory extends JFrame{

        final static int maximumEntries = 300;
        static int numberEntries;
        static InventoryItem[] myInventory = new InventoryItem[maximumEntries];
        JToolBar inventoryToolBar=new JToolBar();
        Border br=BorderFactory.createLineBorder(Color.BLACK,2);
        JButton newButton = new JButton(new ImageIcon("images/new.png"));
        JButton deleteButton = new JButton(new ImageIcon("images/delete.png"));
        JButton saveButton = new JButton(new ImageIcon("images/save.png"));
        JButton previousButton = new JButton(new ImageIcon("images/prev.png"));
        JButton nextButton = new JButton(new ImageIcon("images/next.png"));
        JButton printButton = new JButton(new ImageIcon("images/print.png"));
        JButton exitButton = new JButton(new ImageIcon("images/exit.png"));
        Font f1=new Font("Ariel",Font.BOLD,13);
        JLabel itemLabel = new JLabel("Inventory Item");
        JTextField itemTextField = new JTextField();
        JLabel locationLabel = new JLabel("Location");
        JComboBox locationComboBox = new JComboBox();
        JCheckBox markedCheckBox = new JCheckBox("Marked?");
        JLabel serialLabel = new JLabel("Serial Number");
        JTextField serialTextField = new JTextField();
        JLabel priceLabel = new JLabel("Purchase Price");
        JTextField priceTextField = new JTextField();
        JLabel dateLabel = new JLabel("Date Purchased");
        JDateChooser dateDateChooser = new JDateChooser();
        JLabel storeLabel = new JLabel("Store/Website");
        JTextField storeTextField = new JTextField();
        JLabel noteLabel = new JLabel("Note");
        JTextField noteTextField = new JTextField();
        JLabel photoLabel = new JLabel("Photo");
        static JTextArea photoTextArea = new JTextArea();
        JButton photoButton = new JButton("...");
        PhotoPanel photoPanel = new PhotoPanel();
        JPanel searchPanel = new JPanel();
        JButton[] searchButton = new JButton[26];
        int currentEntry;
        static final int entriesPerPage = 2;
        static int lastPage;
    homeInventory()
    {
        setTitle("Home Inventary mangement System");
        setBounds(400,100,730,570);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent evt)
            {
                exitForm(evt);
            }
        });
        
        add(inventoryToolBar);
        inventoryToolBar.setFloatable(false);
        inventoryToolBar.setBackground(Color.BLUE);
        inventoryToolBar.setOrientation(SwingConstants.VERTICAL);
        inventoryToolBar.setBounds(0,0,84,500);
        inventoryToolBar.addSeparator();
        newButton.setBackground(Color.WHITE);
        Dimension bSize = new Dimension(70,65);
        newButton.setText("New");
        sizeButton(newButton, bSize);
        newButton.setToolTipText("Add New Item");
        newButton.setHorizontalTextPosition(SwingConstants.CENTER);
        newButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        newButton.setFocusable(false);
        newButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                newButtonActionPerformed(e);   
            }   
            
        });

        deleteButton.setText("Delete");
        sizeButton(deleteButton, bSize);
        deleteButton.setBackground(Color.WHITE);
        deleteButton.setToolTipText("Delete Current Item");
        deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
        deleteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButtonActionPerformed(e);   
            }   
            
        });

        saveButton.setText("Save");
        sizeButton(saveButton, bSize);
        saveButton.setBackground(Color.WHITE);
        saveButton.setToolTipText("Save Current Item");
        saveButton.setHorizontalTextPosition(SwingConstants.CENTER);
        saveButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        saveButton.setFocusable(false);
        saveButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               
                saveButtonActionPerformed(e);
                
            }
            
        });

        previousButton.setText("Previous");
        sizeButton(previousButton, bSize);
        previousButton.setBackground(Color.WHITE);
        previousButton.setToolTipText("Display Previous Item");
        previousButton.setHorizontalTextPosition(SwingConstants.CENTER);
        previousButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        previousButton.setFocusable(false);
        previousButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               
                previousButtonActionPerformed(e);
                
            }
            
        });

        nextButton.setText("Next");
        sizeButton(nextButton, bSize);
        nextButton.setBackground(Color.WHITE);
        nextButton.setToolTipText("Display Next Item");
        nextButton.setHorizontalTextPosition(SwingConstants.CENTER);
        nextButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        nextButton.setFocusable(false);
        nextButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                nextButtonActionPerformed(e);
                
            }
            
        });

        printButton.setText("Print");
        sizeButton(printButton, bSize);
        printButton.setBackground(Color.WHITE);
        printButton.setToolTipText("Print Inventory List");
        printButton.setHorizontalTextPosition(SwingConstants.CENTER);
        printButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        printButton.setFocusable(false);
        printButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                printButtonActionPerformed(e);   
            }   
            
        });

        exitButton.setText("Exit");
        exitButton.setBackground(Color.WHITE);
        sizeButton(exitButton, bSize);
        exitButton.setToolTipText("Exit Program");
        exitButton.setHorizontalTextPosition(SwingConstants.CENTER);
        exitButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        exitButton.setFocusable(false);
        exitButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                exitButtonActionPerformed(e);
                
            }
            
        });

        inventoryToolBar.add(newButton);
        inventoryToolBar.add(deleteButton);
        inventoryToolBar.add(saveButton);
        inventoryToolBar.addSeparator();
        inventoryToolBar.add(previousButton);
        inventoryToolBar.add(nextButton);
        inventoryToolBar.addSeparator();
        inventoryToolBar.add(printButton);
        inventoryToolBar.add(exitButton);

        

        itemLabel.setBounds(100,1,100,50);
        itemLabel.setFont(f1);
        itemTextField.setBounds(200,15,500,25);
        itemTextField.setFont(f1);
        itemTextField.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                itemTextFieldActionPerformed(e);
                
            }   
        });
        locationLabel.setBounds(133,40,100,50);
        locationLabel.setFont(f1);
        locationComboBox.setBounds(200,55,400,25);
        locationComboBox.setBackground(Color.white);
        locationComboBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                locationComboBoxActionPerformed(e); 
            }   
        });
        markedCheckBox.setBounds(610,55,100,25);
        markedCheckBox.setFont(f1);
        serialLabel.setBounds(100,80,100,50);
        serialLabel.setFont(f1);
        serialTextField.setBounds(200,95,400,25);
        serialTextField.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                serialTextFieldActionPerformed(e); 
            }   
        });
         
        priceLabel.setBounds(95,120,100,50);
        priceLabel.setFont(f1);
        priceTextField.setBounds(200,135,270,25);
        priceTextField.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                priceTextFieldActionPerformed(e);
                
            }   
        });
        dateLabel.setBounds(485,123,100,50);
        dateLabel.setFont(f1);
        dateDateChooser.setBounds(590,135,110,25);
        dateDateChooser.addPropertyChangeListener(new PropertyChangeListener(){
        
            public void propertyChange(PropertyChangeEvent e)
            {
              dateDateChooserPropertyChange(e);
            }
        
    });
        
        storeLabel.setBounds(103,155,100,50);
        storeLabel.setFont(f1);
        storeTextField.setBounds(200,170,500,25);
        storeTextField.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                storeTextFieldActionPerformed(e);
                
            }    
        });
        noteLabel.setBounds(158,195,100,50);
        noteLabel.setFont(f1);
        noteTextField.setBounds(200,210,500,25);
        noteTextField.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                noteTextFieldActionPerformed(e);
                
            }   
        });
        photoLabel.setBounds(150,245,100,50);
        photoLabel.setFont(f1);
        photoTextArea.setBounds(200,250,440,35);
        photoTextArea.setBackground(new Color(255, 255, 192));
        photoTextArea.setBorder(br);
        photoButton.setBounds(660,255,40,30);
        photoButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                photoButtonActionPerformed(e);
                
            }
            
        });
        add(photoButton);
        add(photoLabel);
        add(photoTextArea);
        add(noteLabel);
        add(noteTextField);
        add(storeTextField);
        add(storeLabel);
        add(dateDateChooser);
        add(dateLabel);
        add(priceTextField);
        add(priceLabel);
        add(serialLabel);
        add(serialTextField);
        add(markedCheckBox);
        add(locationComboBox);
        add(locationLabel);
        add(itemLabel);
        add(itemTextField);
        
        
        searchPanel.setPreferredSize(new Dimension(240, 160));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Item Search"));
        searchPanel.setLayout(null);
        searchPanel.setBounds(100,300,290,180);
        add(searchPanel);
        int x = 10, y = 20;
        int count=0;
        for (int i = 0; i <26; i++)
        {
        
        searchButton[i] = new JButton();
        
        searchButton[i].setText(String.valueOf((char) (65 + i)));
        searchButton[i].setFont(new Font("Arial", Font.BOLD, 12));
        searchButton[i].setBounds(x,y,45,30);
        searchButton[i].setBackground(Color.YELLOW);
        searchPanel.add(searchButton[i]);
        searchButton[i].addActionListener(new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                searchButtonActionPerformed(e);
            }
        });
        count++;
        x+=45;
        if(count==6){
            x=10;
            y+=30;
            count=0;
        }
    }
        
        photoPanel.setLayout(null);
        photoPanel.setBounds(400,300,300,180);
        add(photoPanel);
        
        int n;
        try
        {
        BufferedReader inputFile = new BufferedReader(new FileReader("inventory.txt"));
        numberEntries =Integer.valueOf(inputFile.readLine()).intValue();
        if (numberEntries != 0)
        {
        for (int i = 0; i < numberEntries; i++)
        {
        myInventory[i] = new InventoryItem();
        myInventory[i].description = inputFile.readLine();
        myInventory[i].location = inputFile.readLine();
        myInventory[i].serialNumber = inputFile.readLine();
        myInventory[i].marked = Boolean.valueOf(inputFile.readLine()).booleanValue();
        myInventory[i].purchasePrice = inputFile.readLine();
        myInventory[i].purchaseDate = inputFile.readLine();
        myInventory[i].purchaseLocation = inputFile.readLine();
        myInventory[i].note = inputFile.readLine();
        myInventory[i].photoFile = inputFile.readLine();
        }
        }
        // read in combo box elements
        n = Integer.valueOf(inputFile.readLine()).intValue();
        if (n != 0)
        {
        for (int i = 0; i < n; i++)
        {
        locationComboBox.addItem(inputFile.readLine());
        }
        }
       
        inputFile.close();
        currentEntry = 1;
        showEntry(currentEntry);
        }
        catch (Exception ex)
        {
        numberEntries = 0;
        currentEntry = 0;

        }
        if (numberEntries == 0)
        {
        newButton.setEnabled(false);
        deleteButton.setEnabled(false);
        nextButton.setEnabled(false);
        previousButton.setEnabled(false);
        printButton.setEnabled(false);
        }
        setVisible(true);
        
        

        
}

        private void newButtonActionPerformed(ActionEvent e) {
            checkSave();
            blankValues();
        }
        private void deleteButtonActionPerformed(ActionEvent e) {
            if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?",
            "Delete Inventory Item", JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION)
            return;
            deleteEntry(currentEntry);
            if (numberEntries == 0)
            {
            currentEntry = 0;
            blankValues();
            }
            else
            {
            currentEntry--;
            if (currentEntry == 0)
            currentEntry = 1;
            showEntry(currentEntry);
            }
            

        }
        private void saveButtonActionPerformed(ActionEvent e) {
            itemTextField.setText(itemTextField.getText().trim());
            if(itemTextField.getText().equals(""))
            {
               JOptionPane.showConfirmDialog(null, "Must have item description.", "Error",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
               itemTextField.requestFocus();
               return;
            }
    if (newButton.isEnabled())
    {

        deleteEntry(currentEntry);
    }

    String s = itemTextField.getText();
    itemTextField.setText(s.substring(0, 1).toUpperCase() + s.substring(1));
    numberEntries++;
    currentEntry = 1;
    if (numberEntries != 1)
    {
        do
        {
            if
            (itemTextField.getText().compareTo(myInventory[currentEntry - 1].description) < 0)
                break;
            currentEntry++;
        }
        while (currentEntry < numberEntries);
    }
    if (currentEntry != numberEntries)
    {
        for (int i = numberEntries; i >= currentEntry + 1; i--)
        {
            myInventory[i - 1] = myInventory[i - 2];
            myInventory[i - 2] = new InventoryItem();
        }
    }
    myInventory[currentEntry - 1] = new InventoryItem();
    myInventory[currentEntry - 1].description = itemTextField.getText();
    myInventory[currentEntry - 1].location = locationComboBox.getSelectedItem().toString();
    myInventory[currentEntry - 1].marked = markedCheckBox.isSelected();
    myInventory[currentEntry - 1].serialNumber = serialTextField.getText();
    myInventory[currentEntry - 1].purchasePrice = priceTextField.getText();
    myInventory[currentEntry - 1].purchaseDate = dateToString(dateDateChooser.getDate());
    myInventory[currentEntry - 1].purchaseLocation = storeTextField.getText();
    myInventory[currentEntry - 1].photoFile = photoTextArea.getText();
    myInventory[currentEntry - 1].note = noteTextField.getText();
    showEntry(currentEntry);
    if (numberEntries < maximumEntries)
        newButton.setEnabled(true);
    else
        newButton.setEnabled(false);
    
        deleteButton.setEnabled(true);
        printButton.setEnabled(true); 
    }

        private void previousButtonActionPerformed(ActionEvent e) {
            checkSave();
            currentEntry--;
            showEntry(currentEntry);
        }

        private void nextButtonActionPerformed(ActionEvent e) {
            checkSave();
            currentEntry++;
            showEntry(currentEntry);
        }
        private void exitButtonActionPerformed(ActionEvent e) {
            exitForm(null);
        }

        private void printButtonActionPerformed(ActionEvent e) {
            lastPage = (int) (1 + (numberEntries - 1) / entriesPerPage);
            PrinterJob inventoryPrinterJob = PrinterJob.getPrinterJob();
            inventoryPrinterJob.setPrintable(new InventoryDocument());
            if (inventoryPrinterJob.printDialog())
            {
            try
            {
            inventoryPrinterJob.print();
            }
            
            catch (PrinterException ex)
            {
            JOptionPane.showConfirmDialog(null, ex.getMessage(), "Print Error",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            }
            }
            

        }

        private void searchButtonActionPerformed(ActionEvent e) {
            int i;
            if (numberEntries == 0)
            return;
            
            String letterClicked = e.getActionCommand();
            i = 0;
            do
            {
            if (myInventory[i].description.substring(0, 1).equals(letterClicked))
            {
            currentEntry = i + 1;
            showEntry(currentEntry);
            return;
            }
            i++;
            }
            while (i < numberEntries);
            JOptionPane.showConfirmDialog(null, "No " + letterClicked + " inventory items.","None Found", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
        }

        

     
         private void showEntry(int j)
         {
            itemTextField.setText(myInventory[j - 1].description);
            locationComboBox.setSelectedItem(myInventory[j - 1].location);
            markedCheckBox.setSelected(myInventory[j - 1].marked);
            serialTextField.setText(myInventory[j - 1].serialNumber);
            priceTextField.setText(myInventory[j - 1].purchasePrice);
            dateDateChooser.setDate(stringToDate(myInventory[j - 1].purchaseDate));
            storeTextField.setText(myInventory[j - 1].purchaseLocation);
            noteTextField.setText(myInventory[j - 1].note);
            showPhoto(myInventory[j - 1].photoFile);
            nextButton.setEnabled(true);
            previousButton.setEnabled(true);
            if (j == 1)
            previousButton.setEnabled(false);
            if (j == numberEntries)
            nextButton.setEnabled(false);
            itemTextField.requestFocus();
        }

         private Date stringToDate(String s){
            int m = Integer.valueOf(s.substring(0, 2)).intValue() - 1;
            int d = Integer.valueOf(s.substring(3, 5)).intValue();
            int y = Integer.valueOf(s.substring(6)).intValue() - 1900;
            return(new Date(y, m, d));
         }
     

         private String dateToString(Date dd){
            String yString = String.valueOf(dd.getYear() + 1900);
            int m = dd.getMonth() + 1;
            String mString = new DecimalFormat("00").format(m);
            int d = dd.getDate();
            String dString = new DecimalFormat("00").format(d);
            return(mString + "/" + dString + "/" + yString);
         }
     

         private void showPhoto(String photoFile){
            if (!photoFile.equals(""))
            {
                try
                {
                    photoTextArea.setText(photoFile);
                    System.out.println(photoFile);
                }
                catch (Exception ex)
                {
                    photoTextArea.setText("");
                }
            }
            else
            {
                photoTextArea.setText("");
            }
           photoPanel.repaint();
         }
     

         private void sizeButton(JButton newButto, Dimension bSize) {
            newButto.setPreferredSize(bSize);
            newButto.setMinimumSize(bSize);
            newButto.setMaximumSize(bSize); 
         }
    

         private void locationComboBoxActionPerformed(ActionEvent e){
            if (locationComboBox.getItemCount() != 0){
            for (int i = 0; i < locationComboBox.getItemCount(); i++){
            if(locationComboBox.getSelectedItem().toString().equals(locationComboBox.getItemAt(i).toString()))
            {
                serialTextField.requestFocus();
                return;
            }
        }
    }
            locationComboBox.addItem(locationComboBox.getSelectedItem( ));
            serialTextField.requestFocus();
}
       
         private void itemTextFieldActionPerformed(ActionEvent e) {
            locationComboBox.requestFocus();
        } 

         private void serialTextFieldActionPerformed(ActionEvent e) {
            priceTextField.requestFocus();
        } 
         private void noteTextFieldActionPerformed(ActionEvent e) {
            photoButton.requestFocus();
        } 
         private void storeTextFieldActionPerformed(ActionEvent e) {
            noteTextField.requestFocus();
       } 
         private void dateDateChooserPropertyChange(PropertyChangeEvent e) {
            storeTextField.requestFocus();
        }
         private void priceTextFieldActionPerformed(ActionEvent e) {
            dateDateChooser.requestFocus();
       } 

         private void exitForm(WindowEvent evt){
            if (JOptionPane.showConfirmDialog(null, "Any unsaved changes will be lost.\nAre you sure you want to exit?", "Exit Program", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION)
        return;

    try
    {
        PrintWriter outputFile = new PrintWriter(new BufferedWriter(new
                FileWriter("inventory.txt")));
        outputFile.println(numberEntries);
        if (numberEntries != 0)
        {
            for (int i = 0; i < numberEntries; i++)
            {
                outputFile.println(myInventory[i].description);
                outputFile.println(myInventory[i].location);
                outputFile.println(myInventory[i].serialNumber);
                outputFile.println(myInventory[i].marked);
                outputFile.println(myInventory[i].purchasePrice);
                outputFile.println(myInventory[i].purchaseDate);
                outputFile.println(myInventory[i].purchaseLocation);
                outputFile.println(myInventory[i].note);
                outputFile.println(myInventory[i].photoFile);
            }
        }

        outputFile.println(locationComboBox.getItemCount());
        if (locationComboBox.getItemCount() != 0)
        {
            for (int i = 0; i < locationComboBox.getItemCount(); i++)
                outputFile.println(locationComboBox.getItemAt(i));
        }
        outputFile.close();
    }
catch (Exception ex)
    {
    }
    System.exit(0);
}

private void photoButtonActionPerformed(ActionEvent e){
    JFileChooser openChooser = new JFileChooser();
    openChooser.setDialogType(JFileChooser.OPEN_DIALOG);
    openChooser.setDialogTitle("Open Photo File");
    openChooser.addChoosableFileFilter(new FileNameExtensionFilter("Photo Files","jpg"));
    if (openChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
    showPhoto(openChooser.getSelectedFile().toString());
         }
    
    private void blankValues(){
        newButton.setEnabled(false);
        deleteButton.setEnabled(false);
        saveButton.setEnabled(true);
        previousButton.setEnabled(false);
        nextButton.setEnabled(false);
        printButton.setEnabled(false);
        itemTextField.setText("");
        locationComboBox.setSelectedItem("");
        markedCheckBox.setSelected(false);
        serialTextField.setText("");
        priceTextField.setText("");
        dateDateChooser.setDate(new Date());
        storeTextField.setText("");
        noteTextField.setText("");
        photoTextArea.setText("");
        photoPanel.repaint();
        itemTextField.requestFocus();
        
    }
    private void deleteEntry(int j)
    {
    
    if (j != numberEntries)
    {
    
    for (int i = j; i < numberEntries; i++)
    {
    myInventory[i - 1] = new InventoryItem();
    myInventory[i - 1] = myInventory[i];
    }
    }
    numberEntries--;
    }  
    private void checkSave()
    {  
    boolean edited = false;
    if (!myInventory[currentEntry - 1].description.equals(itemTextField.getText()))
    edited = true;
    else if (!myInventory[currentEntry -1].location.equals(locationComboBox.getSelectedItem().toString()))
    edited = true;
    else if (myInventory[currentEntry - 1].marked != markedCheckBox.isSelected())
    edited = true;
    else if (!myInventory[currentEntry - 1].serialNumber.equals(serialTextField.getText()))
    edited = true;
    else if (!myInventory[currentEntry - 1].purchasePrice.equals(priceTextField.getText()))
    edited = true;
    else if (!myInventory[currentEntry -1].purchaseDate.equals(dateToString(dateDateChooser.getDate())))
    edited = true;
    else if (!myInventory[currentEntry -
    1].purchaseLocation.equals(storeTextField.getText()))
    edited = true;
    else if (!myInventory[currentEntry - 1].note.equals(noteTextField.getText()))
    edited = true;
    else if (!myInventory[currentEntry - 1].photoFile.equals(photoTextArea.getText()))
    edited = true;
    if (edited)
    {
    if (JOptionPane.showConfirmDialog(null, "You have edited this item. Do you want to save the changes?", "Save Item", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)saveButton.doClick();
    }}

    public static void main(String[] args) {
        new homeInventory();
    }
    
}
class PhotoPanel extends JPanel{

    public void paintComponent(Graphics g){

        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);
        g2D.setPaint(Color.BLACK);
        g2D.draw(new Rectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1));
        Image photoImage = new ImageIcon(homeInventory.photoTextArea.getText()).getImage();
        int w = getWidth();
        int h = getHeight();
        double rWidth = (double) getWidth() / (double) photoImage.getWidth(null);
        double rHeight = (double) getHeight() / (double) photoImage.getHeight(null);
        if (rWidth > rHeight)
        {
            w = (int) (photoImage.getWidth(null) * rHeight);
            }
            else
            {
            h = (int) (photoImage.getHeight(null) * rWidth);
            }
            
            g2D.drawImage(photoImage, (int) (0.5 * (getWidth() - w)), (int) (0.5 * (getHeight() - h)),w, h, null);
        g2D.dispose();

    }
}

class InventoryDocument implements Printable {
    public int print(Graphics g, PageFormat pf, int pageIndex) {
        Graphics2D g2D = (Graphics2D) g;
        if ((pageIndex + 1) > homeInventory.lastPage) {
            return NO_SUCH_PAGE;
        }
        int i, iEnd;
        g2D.setFont(new Font("Arial", Font.BOLD, 14));
        g2D.drawString("Home Inventory Items - Page " + String.valueOf(pageIndex + 1),(int) pf.getImageableX(), (int) (pf.getImageableY() + 25));

        int dy = (int) g2D.getFont().getStringBounds("S", g2D.getFontRenderContext()).getHeight();
        int y = (int) (pf.getImageableY() + 4 * dy);
        iEnd = homeInventory.entriesPerPage * (pageIndex + 1);
        if (iEnd > homeInventory.numberEntries)
            iEnd = homeInventory.numberEntries;
        for (i = 0 + homeInventory.entriesPerPage * pageIndex; i < iEnd; i++) {

            Line2D.Double dividingLine = new Line2D.Double(pf.getImageableX(), y, pf.getImageableX() + pf.getImageableWidth(), y);g2D.draw(dividingLine);
            y += dy;
            g2D.setFont(new Font("Arial", Font.BOLD, 12));
            g2D.drawString(homeInventory.myInventory[i].description, (int) pf.getImageableX(), y);
            y += dy;
            g2D.setFont(new Font("Arial", Font.PLAIN, 12));
            g2D.drawString("Location: " + homeInventory.myInventory[i].location, (int)(pf.getImageableX() + 25), y);
            y += dy;
            if (homeInventory.myInventory[i].marked)g2D.drawString("Item is marked with identifying information.", (int)(pf.getImageableX() + 25), y);
            else
                g2D.drawString("Item is NOT marked with identifying information.", (int)(pf.getImageableX() + 25), y);
            y += dy;
            g2D.drawString("Serial Number: " + homeInventory.myInventory[i].serialNumber, (int) (pf.getImageableX() + 25), y);
            y += dy;
            g2D.drawString("Price: $" + homeInventory.myInventory[i].purchasePrice + ", Purchased on: " + homeInventory.myInventory[i].purchaseDate, (int) (pf.getImageableX() +25), y);
            y += dy;
            g2D.drawString("Purchased at: " +homeInventory.myInventory[i].purchaseLocation, (int) (pf.getImageableX() + 25), y);
            y += dy;
            g2D.drawString("Note: " + homeInventory.myInventory[i].note, (int)(pf.getImageableX() + 25), y);
            y += dy;
            try {

                Image inventoryImage = new ImageIcon(homeInventory.myInventory[i].photoFile).getImage();
                double ratio = (double) (inventoryImage.getWidth(null)) / (double) inventoryImage.getHeight(null);
                g2D.drawImage(inventoryImage, (int) (pf.getImageableX() + 25), y, (int) (100 *ratio), 100, null);
            } 
            catch (Exception ex) {
            }
            y += 2 * dy + 100;
        }
        return PAGE_EXISTS;
    }
}
