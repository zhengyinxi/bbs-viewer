/**
 * Created by zhengyinxi on 2017/1/11.
 */
import configuration from "../configuration/index";

const host=configuration.host;
const loginUrl = host + "/auth/login";
const logoutUrl = host + "/auth/logout";
const registerUrl = host + "/auth/register";
const forgetUrl = host + "/auth/forget";
const resetUrl = host + "/auth/reset";

export default {
    login: function (data) {
        let url = `${loginUrl}`,
            opt = {
                method: 'post',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            };

        return fetch(url, opt);
    },
    register: function (data) {
        let url = `${registerUrl}`,
            opt = {
                method: 'post',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            };

        return fetch(url, opt);
    },
    forget: function (data) {
        let url = `${forgetUrl}`,
            opt = {
                method: 'post',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            };

        return fetch(url, opt);
    },
    reset: function (data) {
        let url = `${resetUrl}`,
            opt = {
                method: 'post',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            };

        return fetch(url, opt);
    }
}