document.addEventListener('DOMContentLoaded', function() {
    var list = document.getElementById('list');
  
    // 监听整个list容器的change事件
    list.addEventListener('change', function(event) {
      if (event.target.matches('.do-item input[type="checkbox"]')) {
        // 切换文本的删除线状态
        var contentSpan = event.target.closest('.do-item').querySelector('.do-content span');
        contentSpan.style.textDecoration = event.target.checked ? 'line-through' : 'none';
      }
    });

    //监听下拉菜单
    document.getElementById("showFormButton").addEventListener("click", function() {
      document.getElementById("popupForm").style.display = "block";
      document.getElementById("overlay").style.display = "block";
    });
    
    document.getElementById("overlay").addEventListener("click", function() {
      document.getElementById("popupForm").style.display = "none";
      document.getElementById("overlay").style.display = "none";
    });
  });
