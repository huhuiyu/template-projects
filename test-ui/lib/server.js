const SERVER_BASE_URL = 'http://127.0.0.1:20000';
const SERVER_TOKEN_KEY = 'huhuiyu.server.token';
const SERVER = {};

SERVER.getServiceUrl = function () {
  return SERVER_BASE_URL;
};

SERVER.ajax = function (path, params, cb, method) {
  let promise = axios({
    url: SERVER_BASE_URL + path,
    data: Qs.stringify(params, { allowDots: true }),
    method: method ? method : 'post',
    headers: {
      token: localStorage.getItem(SERVER_TOKEN_KEY)
    }
  });
  // 应答结果处理
  promise
    .then(function (resp) {
      if (resp.data && resp.data.token) {
        localStorage.setItem(SERVER_TOKEN_KEY, resp.data.token);
      }
      cb(resp.data);
    })
    .catch(function (err) {
      console.error(err);
      cb({
        code: 500,
        success: false,
        message: '请求失败！'
      });
    });
};

SERVER.formatTimestamp = function (ts, format) {
  format = format ? format : 'yyyy-MM-dd hh:mm:ss';
  let date = new Date();
  date.setTime(ts);
  let y = date.getFullYear();
  let m = date.getMonth() + 1;
  let d = date.getDate();
  let h = date.getHours();
  let mm = date.getMinutes();
  let s = date.getSeconds();
  m = m > 9 ? m : '0' + m;
  d = d > 9 ? d : '0' + d;
  h = h > 9 ? h : '0' + h;
  mm = mm > 9 ? mm : '0' + mm;
  s = s > 9 ? s : '0' + s;
  let result = format.replace(/yyyy/g, y);
  result = result.replace(/MM/g, m);
  result = result.replace(/dd/g, d);
  result = result.replace(/hh/g, h);
  result = result.replace(/mm/g, mm);
  result = result.replace(/ss/g, s);
  return result;
};

SERVER.md5 = function (info) {
  return SparkMD5.hash(info);
};

export default SERVER;