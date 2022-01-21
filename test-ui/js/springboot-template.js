import { server, baseUrl } from '../lib/server.js';
import Vue from '../lib/vue.esm.min.js';
let ws;
let wsChat;
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
      mail: '',
      echo: '',
      echoInfo: '',
      chatInfo: {
        name: '',
        info: ''
      },
      chatResult: ''
    };
  },
  methods: {
    sendChat() {
      wsChat.send(app.chatInfo);
    },
    sendEcho() {
      ws.send(app.echo);
    },
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
    },
    openWs() {
      ws = server.ws('echo');
      ws.addListener('message', function (data) {
        app.echoInfo = data;
      });

      wsChat = server.ws('chat');

      wsChat.addListener('message', function (data) {
        app.chatResult = data;
      });
    }
  },
  mounted() {
    this.openWs();
  }
});
