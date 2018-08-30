'use strict';
const language_CN = {
  "sProcessing": "处理中...",
  "sLengthMenu": "显示 _MENU_ 项结果",
  "sZeroRecords": "没有匹配结果",
  "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
  "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
  "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
  "sInfoPostFix": "",
  "sSearch": "搜索:",
  "sUrl": "",
  "sEmptyTable": "获取数据中，请稍后",
  "sLoadingRecords": "加载数据中...",
  "sInfoThousands": ",",
  "oPaginate": {
      "sFirst": "首页",
      "sPrevious": "上页",
      "sNext": "下页",
      "sLast": "末页"
  },
  "oAria": {
      "sSortAscending": ": 升序",
      "sSortDescending": ": 降序"
  }
}  

// 页面切换
$(function() {
    //侧边导航和内容的切换
    $('.aside').click(function() {
        let _this = $(this);
        let index = _this.index();

        // 侧栏增加样式
        _this.addClass('active')
            .siblings().removeClass('active');
        // 主栏切换
        let content = $('.content');
        let div = content.children(`div:eq(${index-1})`);
        div.show()
            .siblings().hide();

        if(index>1){
          let item = inits[index-2];
          getDatas(item.options,item.handler);
        }

    });

    $('.newSpider').click(function() {
        $('.content>div:eq(1)').toggle();
        $('#newSpider').toggle();
    });

    $('.newPeople').click(function() {

        $('.content>div:eq(2)').toggle();
        $('#newPeople').toggle();
    });

    //logout 登出
    $('.loginout').click(function(){
        var options = {
            'url':'/logout',
            'data':null
        }

        getJson(options)
          .then( () => {
            window.location.href = " ./index.html";
        }).catch( () => {
            window.location.href = " ./index.html";
        })    
      })
    
})

let inits = [
    {
        el:'#Spider',
        columns: [
                {
                    data:null,
                    render:function(data,type,row,meta){
                        return meta.row + 1 + meta.settings._iDisplayStart;  
                    }
                },
                {
                    data:'name',
                    render:function(data,type,row){
                        return `<a href=${row.url} targer="_blank">${data}</a>`;
                    }
                },
                {
                    data:'url'
                },
                {
                    data:'time'
                },
                {
                    data:null,
                    render:function(data,type,row,meta){
                        let name = row.name;
                        let index = meta.row + 1 + meta.settings._iDisplayStart;
                        return `<button class="btn btn-danger" onclick="spiderDelete('${name}','${index}');return false;">删除</button>`;
                    }
                }
            ],
        handler:null,
        options:{
            url: '/spider/get',
            data: {
                pageNum: 1,
                pageSize: 1000
            }  
        }
    },

    {
        el:'#People',
        columns: [

                {
                    data:'name',
                },
                {
                    data:'mail'
                },
                {
                    data:'name',
                    render:function(data,type,row,meta){
                        let name = row.name;
                        let index = meta.row + 1 + meta.settings._iDisplayStart;
                        return `<button class="btn btn-danger" onclick="peopleDelete('${name}','${index}');return false;">删除</button>`;
                    }
                }
            ],
        handler:null,
        options:{
            url: '/contact/get',
            data: {
                pageNum: 1,
                pageSize: 1000
            }
        }
    },
]

$(function() {
    inits.forEach( item => {
          item.handler = $(item.el).DataTable({
              columns:item.columns,
              language: language_CN,
              sorting:false,
              searching: true,
              "deferRender": true,
              iDisplayLength:10,
              "aLengthMenu" : [10, 20, 50], 
          });
        getDatas(item.options,item.handler);
    })

    $('#Data').DataTable( {
        "serverSide": true,
        "autoWidth": true,
        "searching": true,
        "scorting":false,
        "ajax": {
            url: '/DataCollectionSystem/data/get',
            type: 'POST'
        },
        "columns": [
            {
                data:null,
                render:function(data,type,row,meta){
                    return meta.row + 1 + meta.settings._iDisplayStart;  
                }
            },
            {
                data:'source'
            },
            {
                data:'title',
                render:function(data,type,row){
                    return `<a href=${row.url}  targer="_blank">${data}</a>`;
                }
            },
            {
                data:'time'
            }
        ],
        language: language_CN,
        "deferRender": false,
        iDisplayLength:20,
        "aLengthMenu" : [20, 50, 100,200], 
    } );

    // 新建爬虫
    $('#Spideradd').click( e => {
        e.preventDefault();
        let inputs = $('#newSpider .box-body input');
        //如果有空的输入值
        if (![].every.call(inputs, function(input) {
                return input.value != '';
        })) {
            myalert('warning', "尚未填完表格!");
            return;
        }
        const data = {
            name: inputs[0].value,
            url: inputs[1].value,
            title1: inputs[2].value,
            url1: inputs[3].value,
            title2: inputs[4].value,
            url2: inputs[5].value
        };
        getJson({
            url: '/spider/create',
            data: data
        }).then( res => {
            console.log(res);
            if(res.code == 11){
              return myalert('error','添加失败!');
            }
            myalert('success','添加成功!');
        }).catch( err => {
            myalert('error','添加失败!');
        })
    });

    // 新增联系人
    $('#Peopleadd').click(e => {
        e.preventDefault();
        let people = inits[1].handler;
        let _this = $('#Peopleadd');
        let data = {};
        data.name = $('#addUserName').val();
        data.mail = $('#addEmail').val();

        for(let i in data){
          if(data[i] == ''){
            myalert('info','请检查表格!');
            return ;
          }
        }

      getJson({
          url: '/contact/add',
          method:'post',
          data:data
        }).then( res => {
          if(res.code == 11){
            return myalert('error','添加失败!');
          }
          myalert('success','添加成功!');

        }).catch( err => {
            myalert('error','添加失败!');
        })   
       
    })

})
// 更新数据
function getDatas(options,type)
{
    getJson(options).then( res => {
        type.clear();  
        type.rows.add(res.result);
        type.draw(); 
    }).catch( err => {
      
    })
}

// 发送请求
function getJson(obj){
    return new Promise( (resolve,reject)=>{
      var setting = {
        method: 'POST',
        timeout: 10000,
        beforeSend: function() {
            var BASEURL = '/DataCollectionSystem';
            this.url= BASEURL + this.url;
            myalert('success', '发送数据中');
            if(!this.data) return; 
            this.data = (function (string) {  
                var obj = {}, 
                    pairs = string.split('&'), 
                    d = decodeURIComponent,
                    name, 
                    value;  
                $.each(pairs, function (i, pair) {  
                    pair = pair.split('=');  
                    name = d(pair[0]);  
                    value = d(pair[1]);  
                    obj[name] = !obj[name] ? value : [].concat(obj[name]).concat(value);  
                });  
                return JSON.stringify(obj);  
            })(this.data);  

        },
        contentType:"application/json",
        dataType: 'JSON',
      };
      // 更新setting
      $.extend(setting,obj);
      $.ajax(setting)
        .done( res => resolve(res) )
        .fail( err => reject(err) );
    })
}

//删除联系人
function peopleDelete(name,index)
{
  let people = inits[1].handler;
  let tr = $(inits[1].el).children('tbody').children('tr')[index-1];
  let data = {};
  data.name = name;
  data.index = index;
  getJson({
    url:'/contact/delete',
    data:data
  }).then( res => {
      if(res.code == 11){
        return myalert('error','删除失败!');
      }
      myalert('success','删除成功!');
      people.row(tr).remove().draw();

  }).catch( err => {
      console.log(err);
      return myalert('error','删除失败!');
  })
}

//删除爬虫
function spiderDelete(name,index)
{
  let spider = inits[0].handler;
  let tr = $(inits[0].el).children('tbody').children('tr')[index-1];
  let data = {};
  data.name = name;
  data.index = index;
  getJson({
    url:'/spider/delete',
    data:data
  }).then( res => {
      if(res.code == 11){
        return myalert('error','删除失败!');
      }
      myalert('success','删除成功!');
      spider.row(tr).remove().draw();
  }).catch( err => {
       return myalert('error','删除失败!');
  })
}

// 辅助工具
function myalert(type, message) {
    $.message({
        message: message,
        type: type
    })
}
