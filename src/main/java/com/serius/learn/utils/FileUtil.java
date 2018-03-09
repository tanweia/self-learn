package com.serius.learn.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.commons.lang.StringUtils;

/**
 * create by tanweia on Aug 7, 2017
 */
public class FileUtil {
	
	/**
	 * 根据路径创建文件
	 * @param filePath
	 * @return
	 * @throws IOException 
	 */
	public static File createFile(String filePath) throws IOException {
		File file = new File(filePath);
        File fileParent = file.getParentFile();  
		if(!fileParent.exists()){  
		    fileParent.mkdirs();  
		}  
		file.createNewFile(); 
		return file;
	}
	
	/**
	 * 批量删除目录及下属文件
	 * @param dirPaths
	 */
	public static void deleteDirs(String ...dirPaths){
		if(dirPaths != null && dirPaths.length > 0){
			for (String string : dirPaths) {
				deleteDir(string);
			}
		}			
	}
	
    /** 
     * 删除目录（文件夹）以及目录下的文件 
     * @param   sPath 被删除目录的文件路径 
     * @return  目录删除成功返回true，否则返回false 
     */ 
    public static boolean deleteDir(String dirPath) {  
    	if(StringUtils.isEmpty(dirPath)){
    		return false;
    	}
        //如果dirPath不以文件分隔符结尾，自动添加文件分隔符  
        if(!dirPath.endsWith(File.separator)){  
        	dirPath = dirPath + File.separator;  
        }  
        File dirFile = new File(dirPath);  
        //如果dir对应的文件不存在，或者不是一个目录，则退出  
        if(!dirFile.exists() || !dirFile.isDirectory()){  
            return false;  
        }  
        boolean flag = true;  
        //删除文件夹下的所有文件(包括子目录)  
        File[] files = dirFile.listFiles();  
        for (int i = 0; i < files.length; i++) {
            if(files[i].isFile()){ //删除子文件  
                flag = deleteFile(files[i].getAbsolutePath());  
                if(!flag){
                	break;  
                }
            }else{ //删除子目录
                flag = deleteDir(files[i].getAbsolutePath());  
                if(!flag){
                	break;  
                }
            }
        }  
        if(!flag){
        	return false;  
        }
        //删除当前目录
        if(dirFile.delete()){  
            return true;  
        }else{  
            return false;  
        }  
    }  
    
    /** 
     * 删除单个文件 
     * @param filePath
     * @return
     */  
    public static boolean deleteFile(String filePath) {  
       boolean flag = false;  
       File file = new File(filePath);  
        // 路径为文件且不为空则进行删除  
        if(file.isFile() && file.exists()){  
            file.delete();  
            flag = true;  
        }  
        return flag;  
    }  
	
    
    /** 
     * NIO way 
     *  
     * @param filename 
     * @return 
     * @throws IOException 
     */  
    public static byte[] file2byteByNio(File file) {  
        FileChannel channel = null;  
        FileInputStream fs = null;  
        try {  
            fs = new FileInputStream(file);  
            channel = fs.getChannel();  
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());  
            while ((channel.read(byteBuffer)) > 0) {  
                // do nothing  
                // System.out.println("reading");  
            }  
            return byteBuffer.array();  
        } catch (IOException e) {  
            e.printStackTrace();  
            return null;
        } finally {  
            try {  
                channel.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            try {  
                fs.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }
    
    
    /** 
     * 字节数组转文件
     * @param filePath 
     */  
    public static File byte2fileByNio(byte[] byteArray, String filePath) {  
        FileOutputStream out = null;  
        FileChannel fcOut = null;
        try {  
        	File file = createFile(filePath);
            out = new FileOutputStream(file);  
            fcOut = out.getChannel();  
            ByteBuffer buffer = ByteBuffer.wrap(byteArray);  
            buffer.put(byteArray);
            buffer.flip();
            fcOut.write(buffer); 
            return file;
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;
        } finally {  
        	if (fcOut != null) {  
                try {  
                	fcOut.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }
            if (out != null) {  
                try {  
                    out.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    } 
    
    
    /**
     * 写文件
     * @param content
     * @param file
     * @throws IOException
     */
    public static void writeFileByNio(String content, File file) {
        FileOutputStream fos = null;
        FileChannel fc_out = null;
        try {
            fos = new FileOutputStream(file);
            fc_out = fos.getChannel();
            ByteBuffer buf = ByteBuffer.wrap(content.getBytes());
            buf.put(content.getBytes());
            buf.flip();
            fc_out.write(buf);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fc_out) {
                try {
					fc_out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            if (null != fos) {
                try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
    } 
    
    
	/**
	 * 文件转字节数组
	 * @param file
	 * @return
	 */
	public static byte[] file2byte(File file)  
    {  
        byte[] buffer = null;  
        try  
        {  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream();  
            byte[] b = new byte[1024];  
            int n;  
            while ((n = fis.read(b)) != -1)  
            {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        }  
        catch (FileNotFoundException e)  
        {  
            e.printStackTrace();  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        return buffer;  
    }  
  
    /**
     * 字节数组转文件
     * @param byteArray
     * @param filePath
     */
    public static File byte2file(byte[] byteArray, String filePath)  
    {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        try  
        {  
            File file = createFile(filePath);
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(byteArray);  
            return file;
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            return null;
        }  
        finally  
        {  
            if (bos != null)  
            {  
                try  
                {  
                    bos.close();  
                }  
                catch (IOException e)  
                {  
                    e.printStackTrace();  
                }  
            }  
            if (fos != null)  
            {  
                try  
                {  
                    fos.close();  
                }  
                catch (IOException e)  
                {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }
	
}
