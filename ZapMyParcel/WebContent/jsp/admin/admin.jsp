     <jsp:include page="../home/admin_header.jsp"></jsp:include>
      <!--middle container starts-->
      <div id="main_container">
        <div class="raws">
          <!--grid starts here-->
          <div class="grid">
            <table width="100%" border="0" cellspacing="0" cellpadding="7" class="grid">
              <tr>
                <th valign="top">Date</th>
                <th valign="top">ZAP Ref</th>
                <th valign="top">Name</th>
                <th valign="top">Carrier</th>
                <th colspan="2" valign="top">Tracking    No</th>
                <th valign="top">Type</th>
                <th valign="top">Parcels</th>
                <th valign="top">Price</th>
                <th valign="top">Insurance</th>
                <th valign="top">Status</th>
              </tr>
              <tr>
                <td valign="top">03.05.2012</td>
                <td valign="top">1234</td>
                <td valign="top">Pukar Patel</td>
                <td valign="top">CityLink</td>
                <td colspan="2" valign="top">
                <u>MU123456</u>
                <input class="btn" type="button" name="button" id="button" value="Edit" />
                <input class="btn" type="button" name="button" id="button" value="Edit" />
                </td>
                <td valign="top">Agent 1</td>
                <td valign="top">2</td>
                <td valign="top"><p align="right">£28.16</td>
                <td valign="top">Yes</td>
                <td valign="top">en Route</td>
              </tr>
              <tr>
                <td valign="top">28.04.2012</td>
                <td valign="top">964</td>
                <td valign="top">Chirag Patel</td>
                <td valign="top">Yodel</td>
                <td colspan="2" valign="top">
                <u>YC122357</u>
                <input class="btn" type="button" name="button2" id="button2" value="Add" />
                
                </td>
                <td valign="top">Reversed</td>
                <td valign="top">1</td>
                <td valign="top"><p align="right">£9.58</td>
                <td valign="top">No</td>
                <td valign="top">Delivered</td>
              </tr>
              <tr>
                <td valign="top">04.05.2012</td>
                <td valign="top">1364</td>
                <td valign="top">Hitesh Patel</td>
                <td valign="top">&nbsp;</td>
                <td valign="top"><p>&nbsp;</td>
                <td valign="top"><p>&nbsp;</td>
                <td valign="top">Agent 2</td>
                <td valign="top">3</td>
                <td valign="top"><p align="right">£28.74</td>
                <td valign="top">No</td>
                <td valign="top">To be Collected</td>
              </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td colspan="2" valign="top"><p>&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top"><p align="right">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
              </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td colspan="2" valign="top"><p>&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top"><p align="right">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
              </tr>
            </table>
            
            <div id="tnt_pagination">
            <span class="disabled_tnt_pagination">Prev</span>
             <a href="#1">1</a>
             <a href="#2">2</a>
             <a href="#3">3</a>
             <span class="active_tnt_link">4</span>
             <a href="#5">5</a>
             <a href="#6">6</a>
             <a href="#7">7</a>
             <a href="#8">8</a>
             <a href="#9">9</a>
             <a href="#10">10</a>
            <a href="#forwaed">Next</a>
            </div>
            
          </div>
          <!--grid ends here--> 
          
           <!--message boxes examples-->
                  
                  <div class="info" id="2">Info message</div>
                  <div class="success">Successful operation message</div>
                  <div class="warning">Warning message</div>
                  <div class="error">Error message</div>
                  <!--message boxes examples ends-->

        </div>
      </div>
      <!--middle container ends--> 
      
      <jsp:include page="../home/admin_footer.jsp"></jsp:include>
      
    </div>
</body>
</html>
    