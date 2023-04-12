import javax.imageio.IIOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
        JFrame frame;
        JMenuBar menuBar;

        JMenu file, edit;

        JMenuItem newF, saveF, openF;

        JMenuItem cut, copy, paste, selectAll, close;

        JTextArea textArea;

        TextEditor(){
            //Initialize a Frame
            frame = new JFrame("Text Editor");

            // Initialize a JMenuBar
            menuBar = new JMenuBar();

            //Initialize a JTextArea
            textArea = new JTextArea();

            //Initailize a Menu
            file = new JMenu("File");
            edit = new JMenu("Edit");

            //Initialize a file menu
            newF = new JMenuItem("New Window");
            saveF = new JMenuItem("Save File");
            openF = new JMenuItem("Open File");

            //Add Action Listener to file menu item
            newF.addActionListener(this);
            saveF.addActionListener(this);
            openF.addActionListener(this);

            //Add menu item to file menu
            file.add(newF);
            file.add(saveF);
            file.add(openF);

            //Initialize a edit menu
            cut = new JMenuItem("Cut");
            copy = new JMenuItem("Copy");
            paste = new JMenuItem("Paste");
            selectAll = new JMenuItem("Select ALL");
            close = new JMenuItem("Close Window");

            //Add Action Lisner to file menu
            cut.addActionListener(this);
            copy.addActionListener(this);
            paste.addActionListener(this);
            selectAll.addActionListener(this);
            close.addActionListener(this);


            //Add menu item to edit menu
            edit.add(cut);
            edit.add(copy);
            edit.add(paste);
            edit.add(selectAll);
            edit.add(close);

            //Add menu fo Menubar;
            menuBar.add(file);
            menuBar.add(edit);


            //Set MenuBar to Frame
            frame.setJMenuBar(menuBar);

            //Create a content panel
            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(5,5,5,5));
            panel.setLayout(new BorderLayout(0,0));
            //Add text Area to panel
            panel.add(textArea,BorderLayout.CENTER);
            //Create a Scrooll Pane
            JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            //add to scrollpan to panel
            panel.add(scrollPane);
            //add panel to frame
            frame.add(panel);


            frame.setVisible(true);
            frame.setBounds(0,0,500,500);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
            if(actionEvent.getSource() == newF){
                TextEditor textEditor = new TextEditor();
            } else if(actionEvent.getSource() == openF){
                // file chooser
                JFileChooser fileChooser = new JFileChooser("C:");
                int chooseoption = fileChooser.showOpenDialog(null);
                //If we have click option button
                if(chooseoption == JFileChooser.APPROVE_OPTION){
                    //getting selected file
                    File file = fileChooser.getSelectedFile();
                    //Get the file from path;
                    String filepath = file.getPath();
                    try {
                        //Intialize file reader
                        FileReader fileReader = new FileReader(filepath);
                        //Intialize BufferReader
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String intermidiate = "", output = "";
                        //Read content line by line
                        while((intermidiate = bufferedReader.readLine())!=null){
                            output += intermidiate+"\n";
                        }
                        //Set the output String in textArea
                        textArea.setText(output);
                    } catch (IOException ioException){
                        ioException.printStackTrace();
                    }
                }
            } else if(actionEvent.getSource() == saveF){
                //Intialize file picker/Chooser
                JFileChooser fileChooser = new JFileChooser("C:");
                int chooseoption = fileChooser.showSaveDialog(null);
                //chech if it is Sacee button
                if(chooseoption == JFileChooser.APPROVE_OPTION){
                    File file = new File(fileChooser.getSelectedFile().getAbsoluteFile()+".textFile");
                    try{
                        //Intialize file writter
                        FileWriter fileWriter = new FileWriter(file);
                        // Initailize Bufferwritter
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        //write content to text area to file
                        textArea.write(bufferedWriter);
                        bufferedWriter.close();
                    } catch (IOException ioException){
                        ioException.printStackTrace();
                    }
                }
            } else if(actionEvent.getSource() == cut){
                textArea.cut();
            } else if(actionEvent.getSource() == copy){
                textArea.copy();
            } else if(actionEvent.getSource() == paste){
                textArea.paste();
            } else if(actionEvent.getSource() == selectAll){
                textArea.selectAll();
            } else {
                System.exit(0);
            }
    }

    public static void main(String[] args) {
       TextEditor texteditor = new TextEditor();
    }
}
