import CryptoJS from "crypto-js";

const KEY_ENCRYPTION = process.env.NEXT_PUBLIC_ENCRYPTION_KEY;

const EncryptionUtils = {
    /**
     *
     * @param {string} value
     */
    encrypt: (value) => {
        console.log("value to encrypt", value);
        if (!!value) {
            const stringifiedValue = JSON.stringify({ value });
            console.log("stringified value", stringifiedValue);
            const encryptedValue = CryptoJS.AES.encrypt(
                stringifiedValue,
                KEY_ENCRYPTION
            ).toString();
            console.log("encrypted value", encryptedValue);
            console.log("uri encoded value", encodeURIComponent(encryptedValue));
            return encodeURIComponent(encryptedValue);
        }
    },
    decrypt: (value) => {
        console.log("value to decrypt", value);
        if (!!value) {
            const decryptedValue = CryptoJS.AES.decrypt(
                decodeURIComponent(value),
                KEY_ENCRYPTION
            ).toString(CryptoJS.enc.Utf8);
            console.log("decrypted value", decryptedValue);
            const parsedValue = JSON.parse(decryptedValue);
            console.log("parsed value", parsedValue);
            return parsedValue.value;
        } else {
            return "";
        }
    },
};

export default EncryptionUtils;
