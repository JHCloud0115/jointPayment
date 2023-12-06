import CommonUtils from "./CommonUtils";

const COOKIE_DOMAIN = process.env.NEXT_PUBLIC_COOKIE_DOMAIN;

const CookieUtils = {
    setCookie: (name, value, options = {}) => {
        if (!CommonUtils.isClient()) return;
        const COOKIE_NAME = COOKIE_DOMAIN + "-" + name;
        options = {
            path: "/",
            domain: window.location.href.split("/")[2].includes("localhost")
                ? ""
                : process.env.NEXT_PUBLIC_COOKIE_DOMAIN,
            ...options,
        };
        if (options.expires instanceof Date) {
            options.expires = options.expires.toUTCString();
        }

        let updatedCookie =
            encodeURIComponent(COOKIE_NAME) + "=" + encodeURIComponent(value);

        for (let optionKey in options) {
            updatedCookie += "; " + optionKey;
            let optionValue = options[optionKey];
            if (optionValue !== true) {
                updatedCookie += "=" + optionValue;
            }
        }

        console.log("updatedCookie", updatedCookie);

        document.cookie = updatedCookie;
    },

    /**
     *
     * @param {string} name
     * @return {string | undefined}
     */
    getCookie: (name) => {
        if (!CommonUtils.isClient()) return;
        const COOKIE_NAME = COOKIE_DOMAIN + "-" + name;
        let matches = document.cookie.match(
            new RegExp(
                "(?:^|; )" +
                COOKIE_NAME.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, "\\$1") +
                "=([^;]*)"
            )
        );
        return matches ? decodeURIComponent(matches[1]) : undefined;
    },

    getWebViewCookie: (name) => {
        if (!CommonUtils.isClient()) return;
        let matches = document.cookie.match(
            new RegExp(
                "(?:^|; )" +
                name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, "\\$1") +
                "=([^;]*)"
            )
        );
        return matches ? decodeURIComponent(matches[1]) : undefined;
    },

    /**
     *
     * @param {string} name
     */
    removeCookie: (name) => {
        if (!CommonUtils.isClient()) return;
        CookieUtils.setCookie(name, "", {
            "max-age": -1,
        });
    },
};

export default CookieUtils;
