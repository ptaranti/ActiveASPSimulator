/*****************************************************************
JADE - Java Agent DEvelopment Framework is a framework to develop 
multi-agent systems in compliance with the FIPA specifications.
Copyright (C) 2000 CSELT S.p.A. 

GNU Lesser General Public License

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation, 
version 2.1 of the License. 

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA  02111-1307, USA.
*****************************************************************/

package jade.tools.rma;

import jade.gui.AgentTree;

/**
   
   @author Tiziana Trucco - CSELT S.p.A.
   @version $Date: 2008/10/10 18:41:50 $ $Revision: 1.1 $
 */
class RemoveRemoteAMSAction extends PlatformAction {

  private rma myRMA;

  public RemoveRemoteAMSAction(rma anRMA, ActionProcessor actPro) {
    super ("RemoveRemoteAMSIcon", "Remove Remote Platform", actPro);
    myRMA = anRMA;
  }

  public void doAction(AgentTree.Node node ) {

    	if(node instanceof AgentTree.RemotePlatformNode){
    		//System.out.println("Remove Remote AMS");
    	  myRMA.removeRemotePlatform(((AgentTree.RemotePlatformNode)node).getAPDescription());
    		
    	}
  }

} 