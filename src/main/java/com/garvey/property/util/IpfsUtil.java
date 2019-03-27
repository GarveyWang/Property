package com.garvey.property.util;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * @author GarveyWong
 * @date 2019/3/27
 */
public class IpfsUtil {
    static private IPFS ipfs = new IPFS("/ip4/127.168.1.111/tcp/5001");

    public static String upload(File originFile) throws IOException {
        NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(originFile);
        MerkleNode addResult = ipfs.add(file).get(0);
        return addResult.hash.toString();
    }

    public static void download(String filePathName, String hash) throws IOException {
        Multihash filePointer = Multihash.fromBase58(hash);
        byte[] data = ipfs.cat(filePointer);
        if (data != null){
            File file = new File(filePathName);
            if (file.exists()){
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data, 0, data.length);
            fos.flush();
            fos.close();
        }
    }

    public static void main(String[] args) throws IOException {
        //File file = new File("E:\\go-ipfs\\test.docx");
        //String hash = IpfsUtil.upload(file);
        //IpfsUtil.download("E:\\haha.docx", "QmdfcwkzGSdq1xMqNdMwAgW8e732SqXna52KHsBSi3uw13");
    }
}
