/**
 * @type {boolean}
 * @let
 */
let firstRun = true;

const CommonUtils = {
    /**
     * 현재 클라이언트 사이드인지(마운트된 상태인지) 판별
     * @returns {boolean}
     */
    isClient: () => {
        return typeof window !== "undefined" ? true : false;
    },

    /**
     * 더블클릭 방지
     * @param {function} callback
     * @param {number} timeout
     */
    preventDoubleClick: (callback = () => {}, timeout = 500) => {
        if (firstRun) {
            callback();
        } else {
            return;
        }
        firstRun = false;
        setTimeout(() => {
            firstRun = true;
        }, timeout);
    },

    deepCopyFunction: (originalFunction) => {
        return function () {
            return originalFunction.apply(this, arguments);
        };
    },

    tableNumber: (list, key) => {
        if (list && list?.totalCount > 0) {
            const totalCount = list.totalCount;
            const page = list.page;
            const num = totalCount - ((page - 1) * list.limit + key);

            return num;
        }

        return 1;
    },

    /**
     * @param {string} password
     */
    isPasswordRegex: (password) => {
        if (typeof password !== "string") {
            return false;
        }
        if (password.length < 10 || password.length > 50) {
            return false;
        }
        const lowercase = password.match(/[a-z]/g);
        const uppercase = password.match(/[A-Z]/g);
        const number = password.match(/[0-9]/g);
        const special = password.match(/[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/g);
        // at least 2 of them
        const atLeastTwo =
            [lowercase, uppercase, number, special].filter((v) => !!v).length >= 2;
        return atLeastTwo;
    },
};

export default CommonUtils;
