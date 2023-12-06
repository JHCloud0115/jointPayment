import CookieUtils from "./CookieUtils";
import EncryptionUtils from "./EncryptionUtils";
import ApplicationConstants from "../common/ApplicationConstants";


const LoginUtils = {
    isLogin: () => {
        return (
            typeof CookieUtils.getCookie(ApplicationConstants.ACCESS_TOKEN) !==
            "undefined"
        );
    },

    login: (accessToken) => {
        CookieUtils.setCookie(ApplicationConstants.ACCESS_TOKEN, accessToken);
    },
    /**
     * @param {{backUrl: string}} param
     */
    logout: ({ backUrl = null }) => {
        CookieUtils.removeCookie(ApplicationConstants.ACCESS_TOKEN);

        if (!!backUrl) {
            window.location.href = `/login?backUrl=${EncryptionUtils.encrypt(
                backUrl
            )}`;
            return;
        }
        window.location.href = `/login`;
    },

    getAccessToken: () => {
        return CookieUtils.getCookie(ApplicationConstants.ACCESS_TOKEN);
    },
};

export default LoginUtils;
