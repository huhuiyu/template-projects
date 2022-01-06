import SERVER from '../lib/server.js';

const app = new Vue({
  el: '#app',
  data() {
    return {
      server: SERVER.getServiceUrl(),
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
      SERVER.ajax('/test/mail', app.mailInfo, function (data) {
        app.mail = data;
      });
    },
    sendLombok() {
      SERVER.ajax(
        '/test/lombok',
        {
          info: app.info
        },
        function (data) {
          app.lombok = data;
        }
      );
    }
  }
});
