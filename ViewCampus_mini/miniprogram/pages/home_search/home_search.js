// pages/home_search/home_search.js
var QQMapWX = require("../../lib/qqmap-wx-jssdk.js");
var qqmapsdk=new QQMapWX({
  key:'2X3BZ-PXBCI-C7GGI-UZ5XQ-MZZ4Q-WGBFQ'
});
Page({

  /**
   * 页面的初始数据
   */
  data: {
    inputtext:""
  },

//在Page({})中使用下列代码
//数据回填方法
backfill: function (e) {
  var id = e.currentTarget.id;
  for (var i = 0; i < this.data.suggestion.length;i++){
    if(i == id){
      this.setData({
        inputtext: this.data.suggestion[i].title
      });
    }  
  }
},
//触发关键词输入提示事件
getsuggest: function(e) {
  var _this = this;
  //调用关键词提示接口
  qqmapsdk.getSuggestion({
    //获取输入框值并设置keyword参数
    keyword: e.detail.value, //用户输入的关键词，可设置固定值,如keyword:'KFC'
    region: "开封市",
    region_fix: 1,
    success: function(res) {//搜索成功后的回调
      console.log(res);
      var sug = [];
      for (var i = 0; i < res.data.length; i++) {
        sug.push({ // 获取返回结果，放到sug数组中
          title: res.data[i].title,
          id: res.data[i].id,
          addr: res.data[i].address,
          city: res.data[i].city,
          district: res.data[i].district,
          latitude: res.data[i].location.lat,
          longitude: res.data[i].location.lng
        });
      }
      _this.setData({ //设置suggestion属性，将关键词搜索结果以列表形式展示
        suggestion: sug
      });
    },
    fail: function(error) {
      console.error(error);
    },
    complete: function(res) {
      console.log(res);
    }
  });
},

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})