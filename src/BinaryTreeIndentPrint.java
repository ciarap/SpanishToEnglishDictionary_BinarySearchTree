class BinaryTreeIndentPrint

{

    // Root of the Binary Tree

    Node root;
    String child;



    public BinaryTreeIndentPrint()

    {

        root = null;

    }

void printPreOrder(Node root, String indent,String child)

    {

        if(root == null)

            return;

        System.out.println(""+indent+child+":"+root.getSpanish());

        if(root.getLeftTree() != null){
            printPreOrder(root.getLeftTree(),indent+"   ","L");

        }

        if(root.getRightTree() != null){

            printPreOrder(root.getRightTree(),indent+"   ","R");

        }

    }

     



}