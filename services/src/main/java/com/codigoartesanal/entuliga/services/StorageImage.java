package com.codigoartesanal.entuliga.services;

import java.io.IOException;

/**
 * Created by betuzo on 15/01/16.
 */
public interface StorageImage {
    boolean writeLogo(byte[] file, String path);

    boolean writeFoto(byte[] file, String path);

    void deleteLogo(String path);

    void deleteFoto(String path);
}
