import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        /*String content = "i like like like java do you like a java";
        //获取byte数组
        byte[] contentBytes = content.getBytes();
        System.out.println(Arrays.toString(contentBytes));
        byte[] huffmanCodesBytes = huffmanzip(contentBytes);
        System.out.println(Arrays.toString(huffmanCodesBytes));
        byte[] originalByte = decode(huffmanCodes, huffmanCodesBytes);
        System.out.println(new String(originalByte));*/

        //测试压缩文件
        zipFile("/Users/quon/IdeaProjects/javaDataStruct/src/asset/test2.png", "/Users/quon/IdeaProjects/javaDataStruct/src/asset/test.zip");
        unZipFile("/Users/quon/IdeaProjects/javaDataStruct/src/asset/test.zip", "/Users/quon/IdeaProjects/javaDataStruct/src/asset/test.png");

    }

    //编写一个方法，完成对压缩文件的解压
    public static void unZipFile(String zipFile, String dstFile) {
        //定义文件出入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件的输入流
            is = new FileInputStream(zipFile);
            //对象流
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> map = (Map<Byte, String>) ois.readObject();
            byte[] decodeFile = decode(map, huffmanBytes);
            os = new FileOutputStream(dstFile);
            os.write(decodeFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                assert is != null;
                is.close();
                assert ois != null;
                ois.close();
                assert os != null;
                os.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * 编写方法，将一个文件进行压缩
     *
     * @param srcFile 传入的希望压缩的文件的全路径
     * @param dstFile 我们压缩后将压缩文件放到哪个目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        //创建一个文件的输入流
        FileInputStream fileInputStream = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            fileInputStream = new FileInputStream(srcFile);
            byte[] b = new byte[fileInputStream.available()];
            fileInputStream.read(b);
            System.out.println(b.length);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanzip(b);
            System.out.println(huffmanBytes.length);
            //创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanBytes);//先把赫夫曼的bytes放进去
            //这里是我们以对象流的方式写入赫夫曼编码，是为了以后我们恢复源文件时使用
            //这里一定要把赫夫曼编码写入到压缩文件
            oos.writeObject(huffmanCodes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fileInputStream != null;
                fileInputStream.close();
                assert oos != null;
                oos.close();
                os.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    //解压
    //完成思路，1，先把huffmanCodesBytes装换成对应的二进制的字符串
    //2,赫夫曼对应的二进制字符串重新转成原始的字节数组


    //编写一个方法，完成对压缩数据的解码

    /**
     * @param huffmanCodes 赫夫曼编码表
     * @param huffmanBytes 赫夫曼编码过后的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1,先得到赫夫曼byte对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = i == huffmanBytes.length - 1;
            String byteStr = byteToBitString(!flag, huffmanBytes[i]);
            stringBuilder.append(byteStr);
        }
        //把字符创按照指定的赫夫曼编码进行解码
        //把赫夫曼编码进行调换，因为要反向查询
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        int index = 0;
        for (int i = 1; i <= stringBuilder.length(); i++) {
            String substring = stringBuilder.substring(index, i);//包头不包尾
            Byte aByte = map.get(substring);
            if (aByte != null) {
                index = i;
                list.add(aByte);
            }
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }


    /**
     * 将一个byte转换成一个二进制的字符串
     *
     * @param b
     * @param flag 标识是否需要补高位，true表示需要补高位，如果是false表示不补
     * @return 是该b对应的二进制的字符串，（注意是按照补码返回的）
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用一个变量保存b
        int temp = b;//将b转成int
        //如果是正数，还存在补高位的问题
        if (flag) {
            temp |= 256;//按位与
        }
        String str = Integer.toBinaryString(temp);//返回的是temp对应的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }


    /**
     * 使用一个方法，将前面的方法封装起来，便于我们的调用
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 经过赫夫曼编码处理后得字节数组
     */
    private static byte[] huffmanzip(byte[] bytes) {
        //创建字节数组转成一个赫夫曼节点集
        List<HuffmanNode> nodes = getNodes(bytes);
        //生成赫夫曼树
        HuffmanNode huffmanNodes = creatHuffmanTree(nodes);
        //获取赫夫曼编码表
        Map<Byte, String> huffmanCodes = getCodes(huffmanNodes);
        //根据生成的赫夫曼编码，压缩得到压缩后得赫夫曼编码字节数组
        return zip(bytes, huffmanCodes);
    }


    //编写一个方法，将一个字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码表压缩后得byte[]数组

    /**
     * @param bytes        原始的字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码表
     * @return 返回赫夫曼编码处理后得byte[] ,即8位对应一个byte，放入到byte数组中
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //先利用赫夫曼编码表转成字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //System.out.println(stringBuilder.toString());
        //将对应的字符串转成byte数组
        //统计长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后得byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {//因为是每8位对应一个byte，所以步长+8
            String strBytes;
            if (i + 8 > stringBuilder.length()) {//不够8位
                strBytes = stringBuilder.substring(i);
            } else {
                strBytes = stringBuilder.substring(i, i + 8);
            }
            //将strByte转换成byte，放入到by中
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strBytes, 2);
            index++;
        }
        return huffmanCodeBytes;
    }


    //生成赫夫曼树对应的赫夫曼编码
    //1,将赫夫曼编码表存放在map中
    private static Map<Byte, String> huffmanCodes = new HashMap<>();
    //2,在生成赫夫曼编码表时需要去拼接路径，创建一个stringBuilder，存储某个叶子节点的路径
    private static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便，重载getCode
    private static Map<Byte, String> getCodes(HuffmanNode root) {
        if (root == null) {
            return null;
        }
        //处理root的左左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }


    /**
     * 功能：将出入的node节点的所有叶子节点的赫夫曼编码得到，并放入到huffmanCode集合中
     *
     * @param node          出入节点
     * @param code          路径：左子节点是0，右子节点是1
     * @param stringBuilder 用于拼接路劲
     */
    private static void getCodes(HuffmanNode node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {
            //判断当前这个node是叶子节点还是非叶子节点
            if (node.data == null) {
                //非叶子节点，递归处理
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    private static void preOrder(HuffmanNode root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("为空不能遍历");
        }

    }

    /**
     * @param bytes 接收字符数组
     * @return 返回一个List
     */
    private static List<HuffmanNode> getNodes(byte[] bytes) {
        //创建一个arrayList
        ArrayList<HuffmanNode> nodes = new ArrayList<>();
        //存储每个byte出现的次数
        HashMap<Byte, Integer> byteMap = new HashMap<>();
        for (byte b : bytes) {
            //这个方法就是累加map中的value
            byteMap.merge(b, 1, Integer::sum);
        }
        //把每个键值对转成一个node对象，并加入集合
        for (Map.Entry<Byte, Integer> entry : byteMap.entrySet()) {
            nodes.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //可以通过List 创建对应的赫夫曼树
    private static HuffmanNode creatHuffmanTree(List<HuffmanNode> nodes) {
        while (nodes.size() > 1) {
            //排序，从小到大
            Collections.sort(nodes);
            //取出第一颗最小的二叉树
            HuffmanNode leftNode = nodes.get(0);
            //取出第二小的二叉树
            HuffmanNode rightNode = nodes.get(1);
            //创建一颗新的二叉树
            HuffmanNode parent = new HuffmanNode(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.add(parent);
            //删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
        }
        //nodes 最后的节点，就是赫夫曼树的根节点
        return nodes.get(0);
    }
}

//创建Node，存储数据和权值
class HuffmanNode implements Comparable<HuffmanNode> {
    Byte data;//存放数据本身 比如 'a'=>97
    int weight;//权值
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
