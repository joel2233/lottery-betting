<div class="bet-zone clearfix">
    <span class="toggleBtn"><a href="javascript:void(0);" onclick="toggleBetZone()"><i class="fa fa-angle-double-down"></i></a></span>
    <div class="topZone">
        <div>
            <div class="bet-select fleft">
                <span>已选<b id="selectedCount">0</b>场</span>
            </div>

            <div class="bet-way fleft">
                <span>过关方式:</span>
                <span class="tip" style="color:red;font-weight: bold;">至少选择两场比赛</span>
                <#--<span class="way-item hide"><span class="chkbox" data-value="2串1" data-disabled="0"></span>2串1</span>-->
                <#--<span class="way-item hide"><span class="chkbox" data-value="3串1" data-disabled="0"></span>3串1</span>-->
                <#--<span class="way-item hide"><span class="chkbox" data-value="4串1" data-disabled="0"></span>4串1</span>-->
            </div>

            <div class="top-btn fright"><a href="javascript:void(0);" onclick="window.scrollTo('0','0');">回顶部</a></div>
        </div>

    </div>
    <div class="mainZone">
        <div id="bet-info">

        </div>
    </div>
    <div class="bottomZone">
        <div class="bet-total">
            <span class="bet-bei" style="padding-right: 20px;">
                倍数：
                <span class="numsel">
                    <span class="bei-jian" onclick="minusBs()">
                        -
                    </span>
                    <span class="bei-show">
                        <input type="text" value="1" maxlength="5" id="buy_bs">
                    </span>
                    <span class="bei-jia" onclick="addBs()">
                        +
                    </span>
                </span>
                倍
            </span>
            <span class="bet-price" style="padding-right: 20px;">金额:<span class="price">0</span>元</span>
            <span class="bet-bonus">预计奖金:<span class="bonus">0</span>元</span>
        </div>
        <div>
            <a id="saveBtn" href="javascript:void(0);">保存方案</a>
            <a id="doBet" href="javascript:void(0);">生成投注单</a>
        </div>
    </div>
</div>