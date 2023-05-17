// pages/home/home.js
var QQMapWX = require("../../lib/qqmap-wx-jssdk.js");
var qqmapsdk;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    map_size_change: false,
    map_view_height: 280,
    score_show_height: 310,
    small_image_url: "/images/tabs/to_small.png",
    big_image_url: "/images/tabs/to_big.png",
    include_point: [],
    map_Markers: [],
    mysite:{
      latitude: 34.817022,
      longitude: 114.309333,
    },
    inputtext: '',
    isloading: false,
    page_number: 1,
    eventsList:[]
  },

  /**
   * 地图点击事件
   */
  GetClick(e){
    let _this = this;
    wx.chooseLocation({
      success: function(res){
        _this.setData({
          mysite: {
            latitude: res.latitude,
            longitude: res.longitude
          }
        })
      },
      fail: function(error){
        console.error(error)
      },
      complete: function(res){
        //console.log(res)
      }
    });
  },

  //点击地图标记
  GetEventId(e){
    this.setData({
      inputtext: e.detail.markerId +""
    })
  },

  //改变地图大小
  controlMapSize(){
    let _this = this;
    if(_this.data.map_size_change){
      _this.setData({
        map_view_height: 280,
        score_show_height: 310,
        map_size_change: !_this.data.map_size_change
      })
    }else{
      _this.setData({
      map_view_height: 100,
      score_show_height: 490,
      map_size_change: !_this.data.map_size_change
    })
    }
    
  },

  /**
   * 输入值更正
   */
  inputRespond(e){
    this.setData({
      inputtext: e.detail.value
    });
    //console.log(this.data.inputHint)
  },

  /**
   * 搜索提示
   */
  getditu(){
    
  },

    /**
   * 搜索
   */
  searchEvent(e){
    let eid = e.detail.value;
    wx.request({
      url: 'https://localhost/events/detail/'+eid,
      method:"GET",
      success:(res)=>{
        if(res.data.data[0]==null){
          wx.showToast({
            title: '没有找到该活动',
            icon: 'error',
            duration: 1500
          })
        }else{
          wx.navigateTo({
            url: "/pages/eventSee/eventSee?elist="+ JSON.stringify(res.data.data)
          })
        }
      }
    })
  },

  /**
   * 开始时运行
   * 获取地图视野
   */
  setpoint(){
    this.setData({
      include_point:[
        {"latitude": "34.822739","longitude": "114.303658"},
        {"latitude": "34.822739","longitude": "114.315065"},
        {"latitude": "34.810246","longitude": "114.303658"},
        {"latitude": "34.810246","longitude": "114.315065"}
      ]
    })
  },

  //获得过滤模式
  getFilter(){
    let O = wx.getStorageSync('onlyShowOfficial');
    let I = wx.getStorageSync('onlyShowIndividual');
    O=O==null?false:O;
    I=I==null?false:I;
    if(O==I){
      return 0;
    }else{
      return O==true?1:2;
    }
  },

  /**
   * 开始时运行
   * 获取markers
   */
  getMapMarkers(){
    let _this = this;
    let filter = _this.getFilter();
    wx.request({
      url: 'https://localhost/events/markers/'+filter,
      method: 'GET',
      success: function(res){
        //处理数据为markers
        let mk = [];
        let get_mk = res.data.data;
        get_mk.forEach(e => {
          let Isrc = (e.eventSponsor=="developer")? "marker_1.png":"marker_2.png";
          let temp = {
            iconPath:"/images/tabs/"+Isrc,
            id: parseInt(e.eventId),
            latitude: parseFloat(e.eventLatitude),
            longitude: parseFloat(e.eventLongitude),
            joinCluster: false,
            width: 20,
            height: 20,
            title: e.eventName
          };
          mk.push(temp)
        });
        _this.setData({
          map_Markers: mk
        });
        //console.log(_this.data.map_Markers)
      }
    })
  },

  /**
   * 开始时运行
   * 获取当前地址
   */
  getLocation(){
    let _this = this;
    wx.getLocation({
      type: "gcj02",
      altitude: true,
      highAccuracyExpireTime: 5000,
      success: function(res){
        _this.setData({
          mysite:{
            latitude: res.latitude,
            longitude: res.longitude,
          }
        });
      },
      fail: function(error) {
        console.error(error);
      },
      complete: function(res) {
        //console.log(res);
      }
    })
  },

  //推荐模式
  getRecommend(){
    return wx.getStorageSync('nearRecommend')?1:0
  },

  /**
   * 开始时运行和上拉运行
   * 获取推荐活动
   */
  getEventsList(){
    let _this = this;
    let recommend = _this.getRecommend();
    this.setData({
      isloading:true
    }),
    wx.showLoading({
      title: '加载中...',
    }),
    wx.request({
      url: 'https://localhost/events/get',
      method:'POST',
      data:
      {
        page:_this.data.page_number,
        number:4,
        recommend:recommend
      },
      success:({data:res}) => {
        console.log(res);
        this.setData({
          eventsList: [..._this.data.eventsList,...res.data],
          page_number: _this.data.page_number + 1
        });
        if(res.data.length == 0){
          wx.showToast({
            title: '到底了',
            icon:'error'
          })
        }
      },
      complete:()=>{
        wx.hideLoading(),
        this.setData({
          isloading:false
      })
      }
    })
  },

  //查看点击的活动
  LookThisEvent(e){
    let eid = e.currentTarget.id;
    wx.request({
      url: 'https://localhost/events/detail/'+eid,
      method:"GET",
      success:(res)=>{
        wx.navigateTo({
          url: "/pages/eventSee/eventSee?elist="+ JSON.stringify(res.data.data)
        })
      }
    })
  },

  //下拉刷新
  scrollToLower(){
    if(!this.data.isloading){
      //调用获取推荐活动
     this.getEventsList()
   }else
   {
     return
   }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    //实例化API核心类
    qqmapsdk = new QQMapWX({
      key:"2X3BZ-PXBCI-C7GGI-UZ5XQ-MZZ4Q-WGBFQ"
    });
    //获取include_point
    this.setpoint();
    //获取markers
    this.getMapMarkers();
    //获取当前坐标
    this.getLocation();
    //加载推荐活动
    this.getEventsList()
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
    //获取markers
    this.getMapMarkers();
    //加载推荐活动
    this.getEventsList()
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