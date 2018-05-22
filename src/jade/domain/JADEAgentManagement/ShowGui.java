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


package jade.domain.JADEAgentManagement;

import jade.content.AgentAction;

/**
  This class represents the <code>show-gui</code> action of 
  the <code>JADE-agent-management ontology</code>.
  This action can be requested to the JADE DF to make it show its graphical
  user interface. 

   @author Tiziana Trucco -  CSELT s.p.a
   @version $Date: 2008/10/10 18:41:49 $ $Revision: 1.1 $
*/
public class ShowGui implements AgentAction {

    /**
       Default constructor. A default constructor is necessary for
       ontological classes.
    */
    public ShowGui() {
    }

}
