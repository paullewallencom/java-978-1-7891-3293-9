import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Map;

/**
 * Created by vitthalsrinivasan on 11/10/15.
 */
public class MyTreeModelListener implements TreeModelListener {

    private final Map<DefaultMutableTreeNode, UrlInfo> urlInfoMap;

    public MyTreeModelListener(Map<DefaultMutableTreeNode,UrlInfo> urlInfoMap) {
        this.urlInfoMap = urlInfoMap;
    }

    @Override
    public void treeNodesChanged(TreeModelEvent e) {
        // this is the only method we care about: make sure that when a treenode
        // is edited, we pass the changes on to the underlying URLInfo object
        DefaultMutableTreeNode treeNode  = (DefaultMutableTreeNode)
                (e.getTreePath().getLastPathComponent());
        try{
            int index = e.getChildIndices()[0];
            treeNode = (DefaultMutableTreeNode)(treeNode.getChildAt(index));
            if(urlInfoMap.containsKey(treeNode)){
                urlInfoMap.get(treeNode).setHeadline(treeNode.toString());
            }

        }catch(NullPointerException np) {}
    }

    @Override
    public void treeNodesInserted(TreeModelEvent e) {}

    @Override
    public void treeNodesRemoved(TreeModelEvent e) {}

    @Override
    public void treeStructureChanged(TreeModelEvent e) {}
}
