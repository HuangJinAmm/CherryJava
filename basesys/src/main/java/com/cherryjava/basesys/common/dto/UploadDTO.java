package com.cherryjava.basesys.common.dto;

/**
 * @author hjamm
 */
public class UploadDTO {

    private String url;
    private String fileName;
    private String newFileName;
    private String originalFilename;

    public static class UploadDTOBuilder {
        private UploadDTO uploadDTO;

        public UploadDTOBuilder() {
            this.uploadDTO = new UploadDTO();
        }

        public UploadDTOBuilder url(String url) {
            this.uploadDTO.url = url;
            return this;
        }

        public UploadDTOBuilder fileName(String fileName) {
            this.uploadDTO.fileName = fileName;
            return this;
        }

        public UploadDTOBuilder newFileName(String newFileName) {
            this.uploadDTO.newFileName = newFileName;
            return this;
        }

        public UploadDTOBuilder originalFilename(String originalFilename) {
            this.uploadDTO.originalFilename = originalFilename;
            return this;
        }

        public UploadDTO build() {
            return this.uploadDTO;
        }
    }

    public static UploadDTOBuilder builder() {
        return new UploadDTOBuilder();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }
}
