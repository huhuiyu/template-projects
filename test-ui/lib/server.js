const SERVER_BASE_URL = 'http://127.0.0.1:20000';
const SERVER_TOKEN_KEY = 'huhuiyu.server.token';
const SERVER = {};

SERVER.getServiceUrl = function () {
  return SERVER_BASE_URL;
};

/**
 *
 * @param {string} path 请求路径
 * @param {json} params 请求参数
 * @param {function} cb 回调函数，参数为应答结果
 * @param {boolean} formdata 是否为formdata请求参数（传入且为true才启用formdata，否则为json格式）
 * @param {string} method 请求方式，默认为POST
 */
SERVER.ajax = function (path, params, cb, formdata, method) {
  let promise = axios({
    url: SERVER_BASE_URL + path,
    data: formdata ? Qs.stringify(params, { allowDots: true }) : params,
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
export { SERVER as server, SERVER_BASE_URL as baseUrl };
