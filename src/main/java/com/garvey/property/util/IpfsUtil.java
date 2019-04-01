package com.garvey.property.util;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.springframework.stereotype.Component;

import java.io.*;


/**
 * @author GarveyWong
 * @date 2019/3/27
 */
@Component
public class IpfsUtil {
    static private IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");

    public String upload(File originFile) throws IOException {
        NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(originFile);
        MerkleNode addResult = ipfs.add(file).get(0);
        return addResult.hash.toString();
    }

    public void download(String filePathName, String hash) throws IOException {
        Multihash filePointer = Multihash.fromBase58(hash);
        byte[] data = ipfs.cat(filePointer);
        if (data != null) {
            File file = new File(filePathName);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data, 0, data.length);
            fos.flush();
            fos.close();
        }
    }

    public FileInputStream getFileInputStream(String hash) {
        try {
            Multihash filePointer = Multihash.fromBase58(hash);
            byte[] data = ipfs.cat(filePointer);
            if (data != null) {
                File file = new File("temp");
                FileInputStream fileInputStream = null;

                OutputStream output = new FileOutputStream(file);
                BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
                bufferedOutput.write(data);
                fileInputStream = new FileInputStream(file);
                file.deleteOnExit();
                return fileInputStream;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        IpfsUtil ipfsUtil = new IpfsUtil();
        //File file = new File("E:\\go-ipfs\\test.docx");
//        String hash = ipfsService.upload(file);
//        System.out.println(hash);
        ipfsUtil.download("E:\\haha.docx", "QmdfcwkzGSdq1xMqNdMwAgW8e732SqXna52KHsBSi3uw13");
        //ipfsService.download("E:\\haha.docx", "QmdGQZfPXdfZmk9W5eZLgDjvYh6kUaPcMbW8YAdsLSdbnB", "/test.docx");
    }
}
