import { server, baseUrl } from '../lib/server.js';
import Vue from '../lib/vue.esm.min.js';

const app = new Vue({
  el: '#app',
  data() {
    return {
      server: baseUrl,
      info: '',
      lombok: '',
      mailInfo: {
        to: '1069306849@qq.com',
        subject: '一个新接口的邮件',
        content: '<h1>序列化发送邮件是否稳定？</h1>'
      },
      mail: ''
    };
  },
  methods: {
    sendMail() {
      server.ajax('/test/mail', app.mailInfo, function (data) {
        app.mail = data;
      });
    },
    sendLombok() {
      server.ajax(
        '/test/lombok',
        {
          info: app.info
        },
        function (data) {
          app.lombok = data;
        },
        true
      );
    }
  },
  created() {
    let ws = server.ws('echo');
    ws.onopen = function (ev) {
      console.log('websocket open', ev);
    };
    ws.onclose = function (ev) {
      console.log('websocket close', ev);
    };
    ws.onmessage = function (ev) {
      console.log('websocket message', ev);
      let timews = server.ws('timestamp');
      timews.onopen = function (ev) {
        timews.send(JSON.stringify({}));
      };
    };
    ws.onerror = function (ev) {
      console.log('websocket error', ev);
    };
  }
});
