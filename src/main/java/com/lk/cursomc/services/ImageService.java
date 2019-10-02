package com.lk.cursomc.services;

import com.lk.cursomc.services.exceptions.FileException;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {

    public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {

        // Recupera a extensão do arquivo.
        String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());

        if(!"png".equals(ext) && !"jpg".equals(ext)) {
            throw new FileException("Somente imagens PNG ou JPG são permitidas.");
        }

        try {
            // Le o arquivo e converter para o formato Buffered do JAVA.
            BufferedImage img = ImageIO.read(uploadedFile.getInputStream());

            if("png".equals(ext)) {
                img = pngToJpg(img);
            }

            return img;
        } catch (IOException e) {
            throw new FileException("Erro ao ler o arquivo.");
        }
    }

    public BufferedImage pngToJpg(BufferedImage img) {

        BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        // Color.WHITE => preenche o fundo tramparente do PNG em branco.
        jpgImage.createGraphics().drawImage(img, 0,0, Color.WHITE, null);

        return jpgImage;
    }

    public InputStream getInputStream(BufferedImage img, String extension) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            ImageIO.write(img, extension, os);

            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            throw new FileException("Erro ao ler o arquivo");
        }
    }


    // Recortar a imagem para deixar quadrada
    public BufferedImage cropSquare(BufferedImage sourceImg) {

        // recupera o menor lado da imagem.
        int tamanhoMinimo = (sourceImg.getHeight() <= sourceImg.getWidth()
                            ? sourceImg.getHeight() : sourceImg.getWidth());

        // Recorta a imagem em um quadrado central.
        BufferedImage cropedImage = Scalr.crop(sourceImg,
                                (sourceImg.getWidth() /2) - (tamanhoMinimo /2),
                                (sourceImg.getHeight() /2) - (tamanhoMinimo /2),
                                tamanhoMinimo, tamanhoMinimo);

        return cropedImage;
    }

    // Redimenciona a imagem.
    public BufferedImage resize(BufferedImage sourceImg, int size) {
        return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
    }



}
