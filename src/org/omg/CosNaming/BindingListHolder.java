package org.omg.CosNaming;


/**
* org/omg/CosNaming/BindingListHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from /scratch/jenkins/workspace/8-2-build-linux-amd64/jdk8u241/331/corba/src/share/classes/org/omg/CosNaming/nameservice.idl
* Wednesday, December 11, 2019 2:23:51 AM PST
*/


/**
   * List of Bindings.
   */
public final class BindingListHolder implements org.omg.CORBA.portable.Streamable
{
  public org.omg.CosNaming.Binding value[] = null;

  public BindingListHolder ()
  {
  }

  public BindingListHolder (org.omg.CosNaming.Binding[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = org.omg.CosNaming.BindingListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    org.omg.CosNaming.BindingListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return org.omg.CosNaming.BindingListHelper.type ();
  }

}
