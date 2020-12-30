import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vitthalsrinivasan on 11/10/15.
 */
public class Main {


    // mapping from treen node to the URL info in that tree node.
    private static Map<DefaultMutableTreeNode, UrlInfo> urlInfoMap = new HashMap<>();
    // mapping from the URL to the tree node. THis is needed to add each URL exactly once
    private static Map<String,DefaultMutableTreeNode> treeNodeMap = new HashMap<>();
    // We use these 2 variables above to keep track of the URLs we are processing, as well
    // as the corresponding nodes in the URL tree.


    private static String currentUrl = "http://doxydonkey.blogspot.in";

    public static void main(String[] args){

        //1. lets' create a helper class to encapsulate information for each URL we will be summarizing [DONE]
        //2. let's create a builder for the HTML that we will write to file [DONE]


        //4. once all that is done, we will wire up our UI.

        // Set up the menus
        JMenuBar menuBar = new JMenuBar();
        //Create the menubar
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem menuItem = new JMenuItem("Save");
        menu.add(menuItem);
        // build the menu

        // Set up the editor pane

        JTextArea urlTextArea = new JTextArea(1,30);
        urlTextArea.setText(currentUrl);
        urlTextArea.setEditable(true);
        urlTextArea.setMinimumSize(new Dimension(150, 24));
        urlTextArea.setMargin(new Insets(4,8,4,8));
        // the text area where the contents of the URL can be typed in and edited

        JButton goButton = new JButton("Go!");
        goButton.setPreferredSize(new Dimension(60,33));
        JPanel urlPanel = new JPanel();
        urlPanel.setLayout(new FlowLayout());
        urlPanel.add(urlTextArea);
        urlPanel.add(goButton);
        // the button that loads the URL when clicked


        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setPreferredSize(new Dimension(600,600));
        editorScrollPane.setMinimumSize(new Dimension(10,10));
        // the URL panel that actually holds the URL contents

        // Set up the tree

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Urls");
        DefaultTreeModel treeModel = new DefaultTreeModel(top);
        // this is where we need our tree model listener object
        //3. we will set up tree listeners (since the tree control in Swing is a bit tricky to work with) [DONE]
        treeModel.addTreeModelListener(new MyTreeModelListener(urlInfoMap));
        // set up a treemodel

        JTree urlTree = new JTree(treeModel);
        urlTree.setEditable(true);
        urlTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        urlTree.setShowsRootHandles(true);
        // create a tree

        JScrollPane treeScrollPane = new JScrollPane(urlTree);
        treeScrollPane.setPreferredSize(new Dimension(240,600));
        // allow the tree to scroll as the URLs are added


        // Set up the summaries UI
        JTextArea summaryTextArea = new JTextArea();
        summaryTextArea.setLineWrap(true);
        JScrollPane summaryScrollPane = new JScrollPane(summaryTextArea);
        summaryScrollPane.setPreferredSize(new Dimension(600, 150));
        summaryScrollPane.setBorder(BorderFactory.createEmptyBorder(4,8,4,8));


        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(urlPanel, BorderLayout.NORTH);
        panel.add(treeScrollPane, BorderLayout.WEST);
        panel.add(editorScrollPane,BorderLayout.CENTER);
        panel.add(summaryScrollPane,BorderLayout.SOUTH);
        // Set up the panel that holds the URL panel, tree, editor and summary portions


        JFrame frame = new JFrame("Create snippets from URLs");
        frame.setJMenuBar(menuBar);
        frame.add(panel);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        // Set up the JFrame

        // lastly wire up the listeners

        // 1. the file menu listener (to save to file)
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Save your snippets");
                if (fc.showSaveDialog((editorPane)) == JFileChooser.APPROVE_OPTION) {
                    HtmlWriter.writeToHTML(fc.getSelectedFile().getAbsolutePath(),
                            urlInfoMap.values());
                }
            }
        });

        // 2.the go button listener (to load the url just entered)
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    currentUrl = urlTextArea.getText();
                    // note that this line below has the magic - the url is loaded into
                    // the editorPane window. This can throw an exception, so we need a catch
                    editorPane.setPage(currentUrl);

                    // has this URL been added before?
                    // if not: create a URLInfo object for it, put it in the tree, and
                    // select it
                    // if yes : select it
                    DefaultMutableTreeNode childNode = null;
                    if(treeNodeMap.containsKey(currentUrl)) {
                        childNode = treeNodeMap.get(currentUrl);
                    } else {
                        childNode = new DefaultMutableTreeNode(currentUrl);
                        treeNodeMap.put(currentUrl,childNode);
                        treeModel.insertNodeInto(childNode,top,top.getChildCount());
                    }

                    if(!urlInfoMap.containsKey(childNode)){
                        urlInfoMap.put(childNode,new UrlInfo(currentUrl));
                    }

                    // now all that's left is to select the node (whether newly created or not)
                    TreePath pathToNode = new TreePath(childNode.getPath());
                    urlTree.scrollPathToVisible(pathToNode);
                    urlTree.setSelectionPath(pathToNode);

                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });



        // 3. the tree selection listener, for when the user edits the tree item or selects an item
        urlTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                JTree tree = (JTree) e.getSource();
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)
                        tree.getLastSelectedPathComponent();
                // Oops! we had forgotten to refresh the value of the current url,
                // which caused the bug you just saw. Let's fix that.
                currentUrl = urlInfoMap.get(selectedNode).getUrl();

                // the user selected something. If it was a leaf, update the UI so that
                // the node selected is reflected in the rest of the UI
                if (selectedNode.isLeaf()) {
                    urlTextArea.setText(currentUrl);
                    try {
                        editorPane.setPage(currentUrl);
                        summaryTextArea.setText(urlInfoMap.get(selectedNode).getSummary());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }


            }
        });

        // 4. the summary text area listener, for when the user adds to a particular summary
        summaryTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSummary();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSummary();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSummary();
            }

            private void updateSummary() {
                urlInfoMap.get(treeNodeMap.get(currentUrl)).setSummary(summaryTextArea.getText());
            }
        });



    }

}
