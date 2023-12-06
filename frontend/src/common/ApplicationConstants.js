const ApplicationConstants = {
    ACCESS_TOKEN: "accessToken",
    /** 아이디 정규식
     * - 영어(대소문자를 구별하지 않음) 혹은 숫자 + 특수문자 4종류 (- 하이픈, _ 언더바, . 마침표, @ 골뱅이)
     - @는 1번만 입력 가능
     - 공백없이 숫자만 입력 가능
     - 4~50자 사이
     * @type {string}
     */
    //ID_REGEX: /^(?=.*[a-zA-Z0-9])(?!.*\s)(?!.*@.*@)[a-zA-Z0-9-_.@]{4,50}$/,
    ID_REGEX:
        /^(?=.*[A-Z]|.*[a-z]|.*\d|.*[-_@.])(?!.*[@].*[@])[-a-zA-Z\d_@.]{4,50}$/g,
    EMAIL_REGEX: /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/,

    /**
     * 패스워드 정규식
     * - 대문자, 소문자, 숫자, 특수문자 중 2개 이상 포함
     - 10자리~50자 사이
     */
    // PASSWORD_REG_EXP:
    //   /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!@#$%&*?+_-])[A-Za-z\d$@$!@#$%&*?+_-]{10,50}$/,
    PASSWORD_REG_EXP:
        /^(?=(?:[^a-z]*[a-z]))?(?=(?:[^A-Z]*[A-Z]))?(?=(?:[^\d]*\d))?(?=(?:[^\W_]*[\W_]))(?!.*\s)[A-Za-z\d\W_]*$/,

    NUMBER_REG_EXP: /^[0-9]+$/,
    ENG_REG_EXP: /^[a-zA-z]+$/,
    PHONENUMBER_REG_EXP: /^01(0|1|6|7|8|9)-?([0-9]{3,4})-?([0-9]{4})$/,
    CERT_NUMBER_REG_EXP: /^\d{6}$/,

    INPUT_HANGUL_EN_REG_EXP: /[^ㄱ-ㅎ가-힣a-zA-Z]/gi,
    INPUT_EN_NUMBER_REG_EXP: /[^0-9a-zA-Z]/gi,
    INPUT_EN_REG_EXP: /[^a-zA-Z]/gi,
    INPUT_NUMBER_EXP: /[^0-9]/gi,
    INPUT_HANGUL_EXCEPT: /[^ㄱ-ㅎ가-힣]/gi,

    /**
     * auto completion for email address
     * @type {string[]}
     */
    EMAIL_OPTIONS: [
        "@naver.com",
        "@gmail.com",
        "@kakao.com",
        "@daum.net",
        "@icloud.com",
    ],

    /**
     * 이미지 타입들 정의
     * @type {string[]}
     */
    IMAGE_TYPE: [
        "image/jpeg",
        "image/jpg",
        "image/png",
        "image/gif",
        "image/heic",
        "image/heif",
    ],

    DEFAULT_LIST_SIZE: 20,

    SUCCESS_CODE: "0",
    Y: "Y",
    N: "N",

    /**
     * @type {string[]}
     */
    ACCESS_TOKEN_ERR_CODE_LIST: ["01002", "01003", "03021", "03022"],

    AUTH_COUNT: 180,
};

export default ApplicationConstants;
