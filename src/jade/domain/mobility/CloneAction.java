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

package jade.domain.mobility;

import jade.core.*;

/**

  This class represents the <code>clone-agent</code> action,
  requesting to clone an agent within the platform.

  @author Giovanni Rimassa -  Universita' di Parma
  @version $Date: 2008/10/10 18:41:50 $ $Revision: 1.1 $
*/
public class CloneAction extends MoveAction {

    private String newName;


    /**
       Default constructor. A default constructor is necessary for
       ontological classes.
    */
    public CloneAction() {
    }

    /**
       Set the <code>new-name</code> slot of this action.
       @param nn The new local name (i.e. without the platform ID) for the
       new, cloned agent.
    */
    public void setNewName(String nn) {
      newName = nn;
    }

    /**
       Retrieve the value of the <code>new-name</code> slot of this
       event, containing the new local name (i.e. without the platform ID)
       for the new, cloned agent.
       @return The value of the <code>new-name</code> slot, or
       <code>null</code> if no value was set.
    */
    public String getNewName() {
      return newName;
    }

}
