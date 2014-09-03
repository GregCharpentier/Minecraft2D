
import java.io.*;

public class ReadWrite {
    static public boolean checkExists(String string)
    {
        File f = new File(string+".MC2D");
        return f.exists();
    }
    static public String getContents(File aFile) {
        //...checks on aFile are elided
        StringBuilder contents = new StringBuilder();

        try {
            //use buffering, reading one line at a time
            //FileReader always assumes default encoding is OK!
            BufferedReader input =  new BufferedReader(new FileReader(aFile));
            try {
                String line = null; //not declared within while loop
                /*
                 * readLine is a bit quirky :
                 * it returns the content of a line MINUS the newline.
                 * it returns null only for the END of the stream.
                 * it returns an empty String if two newlines appear in a row.
                 */
                while (( line = input.readLine()) != null){
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }
            }
            finally {
                input.close();
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        return contents.toString();
    }

    static public void setContents(File aFile, String aContents)
    throws  IOException {
        if (aFile == null) {
            throw new IllegalArgumentException("File should not be null.");
        }
        if (!aFile.exists()) {
            System.out.println("**!File does not exist: " + aFile+"!**");
            //throw new FileNotFoundException ("File does not exist: " + aFile);
            aFile.createNewFile();
        }
        if (!aFile.isFile()) {
            throw new IllegalArgumentException("Should not be a directory: " + aFile);
        }
        if (!aFile.canWrite()) {
            throw new IllegalArgumentException("File cannot be written: " + aFile);
        }

        //use buffering
        Writer output = new BufferedWriter(new FileWriter(aFile));
        try {
            //FileWriter always assumes default encoding is OK!
            output.write( aContents );
        }
        finally {
            output.close();
        }
    }
    public static void writeObjectFile(String filename,Object obj)
    {
        FileOutputStream fout;
        ObjectOutputStream objout;
        try
        {
            fout = new FileOutputStream (filename+".MC2D");
            objout = new ObjectOutputStream(fout);
            objout.writeObject(obj);
            fout.close();       
        }
        catch (IOException e)
        {
            System.err.println ("Unable to write to file");
            System.exit(-1);
        }

    }
    public static <T> T readObjectFile(Class<T> theClass,String string)
    {
        FileInputStream fin;
        ObjectInputStream objin;
        try
        {
            fin = new FileInputStream (string+".MC2D");
            objin = new ObjectInputStream(fin);
            T input = null;
            try {
                input = (T)objin.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            objin.close();
            fin.close();
            return input;

        }
        catch (IOException e)
        {
            System.err.println ("Unable to read from file: "+e.getMessage());
            System.exit(-1);
        }
        return null;
    }

    
    public static void write(String fileName,String contents)
    {
        try {
            File file =new File(fileName+".MC2D");
            setContents(file,contents);
            System.out.println(file.getPath());
        }
        catch(java.io.FileNotFoundException r){
            System.err.println("FontFormatException: " + r.getMessage());

        }
        catch(java.io.IOException r){
            System.err.println("FontFormatException: " + r.getMessage());

        }
    }

    public static String read(String fileName)
    {
        return getContents(new File(fileName+".MC2D"));
    }
}
